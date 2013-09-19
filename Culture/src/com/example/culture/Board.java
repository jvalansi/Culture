package com.example.culture;


public class Board {
	
	private Tile [][] b;
	private int width;
	private int length;
	
	public Board() {
		this.width = 12;
		this.length = 12;
		this.b = new Tile[this.width][this.length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				b[i][j] = new Tile(i,j);
			}
		}
	}

	public int size() {
		return this.length*this.width;
	}

	public int getWidth() {
		return this.width;
	}
	
	public int getLength() {
		return this.length;
	}

	public Tile getTile(int place) {
		return this.b[place%width][place/width];
	}
	
	public int distance(int selection, Unit unit) {
		if(unit.getTile() == null){
			return Integer.MAX_VALUE;
		}
		int x_distance = Math.abs(selection%width - unit.getTile().x());
		int y_distance = Math.abs(selection/width - unit.getTile().y());
		return Math.max(x_distance, y_distance);
	}

	public void placeUnit(int place, Unit unit) {
		this.b[place%width][place/width].setUnit(unit);
	}

	public void moveUnit(int place, Unit unit) {
		unit.setTile(null);
		this.b[place%width][place/width].setUnit(unit);
	}

	public void removeUnit(Unit unit) {
		unit.setTile(null);
	}

	public void placeCity(int i, City city) {
		getTile(i).setCity(city);
	}


}
