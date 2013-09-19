package com.example.culture;

import java.util.Random;

import android.R.bool;


/**
 * 
 * @author jordan
 *
 *	Culture Game
 *	Board game
 *	turn based
 *	initial setup: each player starts with a unit in a random location
 *	on each turn player can move his units, or use them to attack enemy units
 *	game ends when player looses all of his units
 */
public class Game {
	
	private Player [] players;
	private int currentPlayerID;
	private Board board;
	private int turn;
	private int maxTurn;
	private int selection;
	
	public Game(Board board) {
		this.players = new Player[2];
		this.players[0] = new Player(true, 0);
		this.players[1] = new Player(false, 1);
		this.currentPlayerID = 0;
		this.board = board;
		Random r = new Random();
		int ri = r.nextInt(board.size());
		this.board.placeUnit(ri, players[0].getUnit(0));
		this.board.placeCity(ri, players[0].getCity(0));
		ri = r.nextInt(board.size());
		this.board.placeUnit(ri, players[1].getUnit(0));
		this.board.placeCity(ri, players[1].getCity(0));
		this.turn = 0;
		this.maxTurn = 9;
		this.selection = 0;
	}
	
	public void setSelection(int selection) {
		this.selection = selection;
	}

	public int getSelection() {
		return selection;
	}

	public int getCurremtPlayerID() {
		return currentPlayerID;
	}
		
	public void gamePlay() {
		if(!gameOver()){
			switchPlayer();			
			playTurn();
		}
	}

	public void switchPlayer() {
		this.currentPlayerID = (this.currentPlayerID + 1)%this.players.length;  
	}

	public void playTurn() {
		Player currentPlayer = players[currentPlayerID];
		Unit unit = currentPlayer.getUnit(0);
		if(!currentPlayer.isHuman()){
			Random r = new Random();
			selection = r.nextInt(board.size());
		}
		if(validAction(selection, unit)){
			performAction(selection, unit);
		}

		turn++;
	}

	public void attackUnit(int place, Unit unit) {
		Unit targetUnit = board.getTile(place).getUnit();
		targetUnit.fight(unit);
		if(targetUnit.getHealth() == 0){
			board.removeUnit(targetUnit);
		}
	}

	private void moveUnit(int selection, Unit unit) {
		board.moveUnit(selection, unit);
		City city = board.getTile(selection).getCity();
		if(city != null){
			city.setId(currentPlayerID);
		}
	}


	public void performAction(int selection, Unit unit) {
		if(isAttack(selection, unit)){
			attackUnit(selection, unit);
		} else {
			moveUnit(selection, unit);
		}	
	}
	
	private boolean validAction(int selection, Unit unit) {
		if(isAttack(selection, unit)){
			return validAttack(selection, unit);
		} else {
			return validMove(selection, unit);
		}
	}

	private boolean isAttack(int selection, Unit unit) {
		Unit targetUnit = board.getTile(selection).getUnit();
		return (targetUnit != null && targetUnit.getId() != currentPlayerID);
	}

	private boolean validAttack(int selection, Unit unit) {
		return board.distance(selection, unit) <= unit.getRange();			
	}

	public boolean validMove(int selection, Unit unit) {
		boolean valid = true;
		valid &= board.distance(selection, unit) <= unit.getMovement();
		valid &= unit.getType().ordinal() == board.getTile(selection).getType().ordinal();
		return valid;
	}

	public boolean gameOver() {
		boolean gameover = false;
		gameover |= isLastOneStanding();
		// TODO
		return gameover;
	}

	private boolean isLastOneStanding() {
		boolean win = true;
		for (int i = 0; i < board.size(); i++) {
			City city = board.getTile(i).getCity();
			Unit unit = board.getTile(i).getUnit();
			boolean noEnemyUnit = city == null || city.getId() != currentPlayerID; 
			boolean noEnemyCity = unit == null || unit.getId() != currentPlayerID;
			boolean noEnemy = noEnemyUnit & noEnemyCity;
			win &= noEnemy;
		}
		return win;
	}

	
}
