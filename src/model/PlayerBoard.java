package model;

import model.utils.Stats;

import java.util.ArrayList;
import java.util.HashSet;

public class PlayerBoard {
	
	
	private HashSet<Case> validsMoves;
	private Case[][] grid;
	private ArrayList<Ship> shipList;
	private Stats stats;


	protected PlayerBoard(Case[][] grid) {
		this.stats = new Stats();

		this.grid = grid;
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid.length; j++){
				grid[i][j] = new Case(i,j);
			}
		}
		this.shipList = new ArrayList<Ship>();
		this.validsMoves = new HashSet<Case>();
		this.initValidsMoves();
	}

	/**
	 * At beginning, all move are Valids.
	 */
	private void initValidsMoves(){
		for (int i = 0; i < this.grid.length ; i++) {
			for (int j= 0; j < this.grid.length ; j++) {
				this.validsMoves.add(this.grid[i][j]);
			}
		}
	}
	
	protected Stats getStats() {
		return this.stats;
	}

	protected Case[][] getGrid(){
		return this.grid;
	}

	/**
	 * The actual statement of a player | If all his ship are dead.
	 * @return
	 */
	protected boolean isDead() {
		for (Ship obj: this.shipList) {
			if (!obj.isDead()) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Ship> getshipList(){
		return this.shipList;
	}

	/**
	 * Add a new ship to his shipList
	 * @param obj
	 */
	protected void addShip(Ship obj) {
		for (Case o: obj.getShip()) {
			o.addShip(obj);
		}
		this.shipList.add(obj);
	}

	protected HashSet<Case> getValidMoves(){
		return this.validsMoves;
	}

}
