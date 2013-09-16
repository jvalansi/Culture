package com.example.culture;


public class Tile {

	private int x;
	private int y;
	public enum Type {LAND, SEA};
	private Unit unit;
	private Type type;
	
	
	public Tile(int x,int y) {
		this.x = x;
		this.y = y;
		this.type = Type.LAND;
		this.unit = null;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
		if(unit != null){
			this.unit.setTile(this);
		}
	}

	public Unit getUnit() {
		return this.unit;
	}

	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
}
