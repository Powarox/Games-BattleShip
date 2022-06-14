package model;

import java.util.ArrayList;

public class Ship {
	
	private ArrayList<Case> ship;
	private String name;
	private boolean dead;
	
	public Ship(ArrayList<Case> ship, String name) {
		this.dead = false;
		this.name = name;
		this.ship = ship;
		for (Case obj: this.ship) {//Set his own reference to all his case.
			obj.addShip(this);
		}

		assert this.ship.size() >= 2 : "A ship cannot be on a simple case, he is suppose to be greater than 2 Cases !!";
	}
	
	public boolean isDead() {
		return this.dead;
	}

	/**
	 * EveryTime a ship is hit, it check is all his case are it, to see if the ship is dead.
	 * @return
	 */
	private boolean checkDead() {
		for (Case obj: this.ship) {
			if (!obj.isHit()) {
				return false;
			}
		}
		return true;
	}
	
	public void hit() {
		if(checkDead()) {
			this.dead = true;
		}
	}

	/**
	 * Like a ship'size is suppose to be greater than 2 cases
	 * Take the 2 first case and determine the direction
	 * @return TRUE means HORIZONTAL and FALSE means VERTICAL
	 */
	public boolean getDirection(){
		Case one = this.ship.get(0);
		Case two = this.ship.get(1);

		if (one.getPosi() == two.getPosi()){
			return false;
		}
		else {
			return true;
		}
	}

	public Case getOrigin(){
		return this.ship.get(0);
	}

	public String getName() {
		return this.name;
	}
	
	public ArrayList<Case> getShip(){
		return this.ship;
	}
	
}
