package com.example.culture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import android.R.bool;
import android.graphics.YuvImage;


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
		int ri;
		do{
			ri = r.nextInt(board.size());
		} while(board.getTile(ri).getType() != Tile.Type.LAND);
		this.board.placeUnit(ri, players[0].getUnit(0));
		this.board.placeCity(ri, players[0].getCity(0));
		do{
			ri = r.nextInt(board.size());
		} while(board.getTile(ri).getType() != Tile.Type.LAND);
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
		if(currentPlayer.armySize()==0){
			return;
		}
		Unit unit = currentPlayer.getUnit(0);
		if(!currentPlayer.isHuman()){
			Random r = new Random();
			ArrayList<Integer> unitActions = getValidActions(unit, this.board);
			int bestScore = 0;
			for (Iterator action = unitActions.iterator(); action.hasNext();) {
				Integer target = (Integer) action.next();
				Board newBoard = new Board(this.board);
				Tile t = unit.getTile(); 
				Unit newUnit = newBoard.getTile(t.x(),t.y()).getUnit();
				performAction(target, newUnit, newBoard);
				int score = getScore(currentPlayerID, newBoard);
				if(score > bestScore ) {
					bestScore = score;
					selection = target;
				}
			}
			selection = unitActions.get(r.nextInt(unitActions.size()));
		}
		if(validAction(selection, unit, this.board)){
			performAction(selection, unit, this.board);
		}

		turn++;
	}

	public void performAction(int selection, Unit unit, Board board) {
		if(isAttack(selection, unit, board)){
			attackUnit(selection, unit, board);
		} else {
			moveUnit(selection, unit, board);
		}	
	}
	
	private void attackUnit(int place, Unit unit, Board board) {
		Unit targetUnit = board.getTile(place).getUnit();
		targetUnit.fight(unit);
		if(targetUnit.getHealth() == 0){
			board.removeUnit(targetUnit);
			players[targetUnit.getId()].removeUnit(targetUnit);
		}
	}

	private void moveUnit(int selection, Unit unit, Board board) {
		board.moveUnit(selection, unit);
		City city = board.getTile(selection).getCity();
		if(city != null){
			players[city.getId()].removeCity(city);
			players[currentPlayerID].addCity(city);
		}
	}

	private boolean validAction(int selection, Unit unit, Board board) {
		if(isAttack(selection, unit, board)){
			return validAttack(selection, unit, board);
		} else {
			return validMove(selection, unit, board);
		}
	}

	private boolean isAttack(int selection, Unit unit, Board board) {
		Unit targetUnit = board.getTile(selection).getUnit();
		return (targetUnit != null && targetUnit.getId() != currentPlayerID);
	}

	private boolean validAttack(int selection, Unit unit, Board board) {
		return board.distance(selection, unit) <= unit.getRange();			
	}

	private boolean validMove(int selection, Unit unit, Board board) {
		boolean valid = true;
		valid &= board.distance(selection, unit) <= unit.getMovement();
		valid &= unit.getType().ordinal() == board.getTile(selection).getType().ordinal();
		return valid;
	}

	public boolean gameOver() {
		boolean gameover = false;
		gameover |= isLastOneStanding(this.board);
		return gameover;
	}

	private boolean isLastOneStanding(Board board) {
		boolean win = true;
		for (int i = 0; i < players.length; i++) {
			if(i==currentPlayerID){
				continue;
			}
			win &= getScore(i, board) == 0;;
		}
		return win;
	}

	private ArrayList<Integer> getValidActions(Unit unit, Board board){
		ArrayList<Integer> validActions = new ArrayList<Integer>(); 
		for (int i = 0; i < board.size(); i++) {
			if(validAction(i, unit, board)){
				validActions.add(i);
			}
		}
		return validActions;
	}
	
	public int getScore(int id, Board board) {
		int score =0;
		for (int i = 0; i < board.size(); i++) {
			Tile tile = board.getTile(i);
			Unit unit = tile.getUnit();
			if(unit != null && unit.getId() == id){
				score ++;
			}
			City city = tile.getCity();
			if(city != null && city.getId() == id){
				score++;
			}
		}
		return score;
	}

	
	
}
