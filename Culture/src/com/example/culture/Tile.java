package com.example.culture;

import java.util.Random;


public class Tile {

	private int x;
	private int y;
	public enum Type {LAND, SEA};
	private Unit unit;
	private Type type;
	private City city;
	
	
	public Tile(int x,int y) {
		this.x = x;
		this.y = y;
		Random r = new Random();
		this.setType(Type.values()[r.nextInt(Type.values().length)]);
		this.unit = null;
		this.city = null;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setCity(City city) {
		this.city =city;
	}

	public City getCity() {
		return city;
	}
	
}
