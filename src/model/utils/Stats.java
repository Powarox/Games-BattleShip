package model.utils;

public class Stats {
	
	private int shootNumber,shootSucess;
	
	public Stats() {
		this.shootNumber = 0;
		this.shootSucess = 0;
	}

	public void hit(boolean sucess) {
		if (sucess) {
			this.shootNumber++;
			this.shootSucess++;
		}else {
			this.shootNumber++;
		}
	}
	public float getRatio(){
		return 100*(float)this.shootSucess/(float)this.shootNumber;
	}

	public int getSucess() {
		return this.shootSucess;
	}
	
	public int getShootNumber() {
		return this.shootNumber;
	}
}
