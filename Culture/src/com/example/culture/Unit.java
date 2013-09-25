package com.example.culture;



/**
 * 
 * @author jvalansi
 *
 *		* unit properties:
 *			* contact: yes/no/both
 *			* defense power
 *			* attack power
 *			* attack range
 *			* attack accuracy
 *			* speed
 *			* sight
 *			* available terrain
 * 			* people
 * 
 */
public class Unit {

	public enum Type {LAND, SEA, AIR};
	private Type type;
	private int id;
	private int attack;
	private int defence;
	private int range;
	private int movement;
	private int sight;
	private int health;
	private Tile tile;
	
	
	public Unit(int id) {
		this.id = id;
		setType(Type.LAND);
		attack = defence = movement = sight = health = range = 1;
		tile = null;
	}
	
	public Unit(Unit unit) {
		this(unit.id);
	}

	public int getId() {
		return id;
	}
	
	public void setTile(Tile tile) {
		if(this.tile != null){
			this.tile.setUnit(null);
		}
		this.tile = tile;
	}

	public Tile getTile() {
		return tile;
	}

	public int getMovement() {
		return movement;
	}

	public int getRange() {
		return range;
	}

	public void fight(Unit unit) {
		this.health -= unit.attack;
	}

	public int getHealth() {
		return health;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
