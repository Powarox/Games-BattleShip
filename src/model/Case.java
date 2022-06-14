package model;

public class Case {
	
	private int posi;
	private int posj;
	private boolean hit;
	private Ship ship;

	/**
	 * Coordinates
	 * @param posi
	 * @param posj
	 */
	public Case(int posi,int posj) {
		this.posi = posi;
		this.posj = posj;
		this.hit = false;
		this.ship = null;
	}

	public int getPosi(){
		return this.posi;
	}

	public int getPosj(){
		return  this.posj;
	}

	/**
	 * Convert string character to Integer
	 * @param posi
	 * @param game
	 * @return
	 */
	public static Integer toInt(String posi, BattleShip game) { // A = 65, Z = 90
		int p = (int) posi.toUpperCase().charAt(0);
		p -= 65;

		if (p > game.getLenght().getWidth() || p < 0){
			return null;
		}
		return p;
	}


	/**
	 * Method'design to hit the case
	 */
	protected void hit() {
		this.hit = true;
		if (this.isShip()) {
			this.ship.hit();
		}
	}
	
	public boolean isHit() {
		return this.hit;
	}

	/**
	 * Check is a ship is present on this case.
	 * @return a True if a ship is present
	 */
	public boolean isShip() {
		return this.ship instanceof Ship;
	}


	protected void addShip(Ship obj) {
		this.ship = obj;
	}
	
	@Override
	public boolean equals(Object o) {
		Case obj = (Case) o;
		if (this.posi == obj.posi && this.posj == obj.posj) {
			return true;
		}
		return false;
		}

		@Override
	public String toString(){
		return "i: " + this.posi + " j : " + this.posj;
		}

}
