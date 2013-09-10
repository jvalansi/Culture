package com.example.culture;

import java.util.Random;

/**
 * 
 * @author jordan
 *
 *	Tic Tac Toe Game
 *	Board game
 *	turn based
 *	on each turn player selects empty tile to place a unit
 *	game ends when a whole line is covered by player's units, or when board is full
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
		this.players[0] = new Player(true);
		this.players[1] = new Player(false);
		this.currentPlayerID = 0;
		this.board = board;
		this.turn = 0;
		this.maxTurn = 9;
		this.selection = 0;
	}
	
	public void setSelection(int selection) {
		this.selection = selection;
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
		if(!players[currentPlayerID].isHuman()){
			Random r = new Random();
			selection = r.nextInt(board.size());
		}
		
		board.setTile(selection, currentPlayerID);
		turn++;
	}

	public boolean gameOver() {
		boolean gameover = false;
		gameover |= (turn == maxTurn);
		for (int i = 0; i < board.getWidth(); i++) {
			gameover |= win(board.getColumn(i));
		}
		for (int i = 0; i < board.getLength(); i++) {
			gameover |= win(board.getRow(i));
		}
		gameover |= win(board.getDiagonal(false));
		gameover |= win(board.getDiagonal(true));
		return gameover;
	}
	
	private boolean win(Tile [] tiles) {
		boolean win = true;
		for (int i = 0; i < tiles.length; i++) {
			win &= (tiles[i].get() == currentPlayerID);
		}
		return win;
	}

	public static void main(String[] args) {
		Board b = new Board();
		Game g = new Game(b);
		g.gamePlay();
	}

}
