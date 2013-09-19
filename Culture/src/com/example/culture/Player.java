package com.example.culture;

import java.util.ArrayList;

public class Player {
	private boolean isHuman;
	private int id;
	private ArrayList<Unit> army;
	private ArrayList<City> cities;
	
	public Player(boolean isHuman, int id) {
		this.id = id;
		this.setHuman(isHuman);
		this.army = new ArrayList<Unit>();
		this.army.add(new Unit(id));
		this.cities = new ArrayList<City>();
		this.cities.add(new City(id));
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	public int armySize(){
		return army.size();
	}
	
	public Unit getUnit(int i) {
		return this.army.get(i);
	}

	public int citiesSize(){
		return cities.size();
	}	
	
	public City getCity(int i) {
		return cities.get(i);
	}

}
