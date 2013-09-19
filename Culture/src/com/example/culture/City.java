package com.example.culture;

public class City {

	private int id;
	private int population;

	public City(int id){
		this.id = id;
		this.population = 1;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getPopulation(){
		return population;
	}
}
