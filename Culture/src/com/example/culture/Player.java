package com.example.culture;

public class Player {
	private boolean isHuman;
	
	public Player(boolean isHuman) {
		this.setHuman(isHuman);
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

}
