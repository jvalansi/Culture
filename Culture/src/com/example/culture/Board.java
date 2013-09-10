package com.example.culture;

public class Board {
	
	private Tile [][] b;
	private int width;
	private int length;
	
	public Board() {
		this.width = 3;
		this.length = 3;
		this.b = new Tile[this.width][this.length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				b[i][j] = new Tile();
			}
		}
	}

	public int size() {
		return this.length*this.width;
	}

	public int getWidth() {
		return this.width;
	}
	
	public void setTile(int selection, int id) {
		b[selection/this.width][selection%this.length].set(id); 
		
	}

	public int getTile(int position) {
		return b[position/this.width][position%this.length].get();
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return this.length;
	}

	public Tile [] getRow(int i) {
		return b[i];
	}
	public Tile [] getColumn(int i) {
		Tile [] tiles = new Tile[length];
		for (int j = 0; j < length; j++) {
			tiles[j] = b[j][i];
		}
		return tiles;
	}

	public Tile [] getDiagonal(boolean backwards) {
		Tile [] tiles = new Tile[length];
		if(backwards){
			for (int i = 0; i < length; i++) {
				tiles[i] = b[width-i-1][i];
			}
		} else {
			for (int i = 0; i < length; i++) {
				tiles[i] = b[i][i];
			}			
		}
		return tiles;
	}

}
