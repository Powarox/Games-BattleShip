package model.player;

import model.BattleShip;
import model.Case;

import java.util.ArrayList;
import java.util.Random;

public class IA extends Human {

	@Override
	public Case chooseMove(BattleShip model) {
		Random rand = new Random();
		ArrayList<Case> validMove = new ArrayList<>(model.validsMoves(this));
		if (validMove.size() > 0 ){
			int index = rand.nextInt(validMove.size());
			Case hit = validMove.get(index);
 			return hit;
		}
		return null;
	}

}
