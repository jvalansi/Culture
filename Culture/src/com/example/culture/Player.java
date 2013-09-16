package com.example.culture;

import java.util.ArrayList;

public class Player {
	private boolean isHuman;
	private int id;
	private ArrayList<Unit> military;
	
	public Player(boolean isHuman, int id) {
		this.id = id;
		this.setHuman(isHuman);
		this.military = new ArrayList<Unit>();
		this.military.add(new Unit(id));
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	public int militrySize(){
		return military.size();
	}
	
	public Unit getUnit(int i) {
		return this.military.get(i);
	}

}
