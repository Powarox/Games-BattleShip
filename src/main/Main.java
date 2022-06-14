package main;

import model.BattleShip;
import model.player.Human;
import model.player.IA;
import view.graphics.GUI;
import java.util.ArrayList;
import java.util.Arrays;

import model.utils.Difficulty;
import model.utils.Length;
import view.terminal.UI;


public class Main {

	private static final int WIDTH = 937;//COEF WIDTH/HEIGHT = 1.875, Pour que le jeu soit affiché corectement
	private static final int HEIGHT = 500;

	public static void command() {
		String type = "2 differents type : Terminal | Graphics, if you put nothing Terminal is the default type.";
		String length = "3 differents Length : \"Short\" = 8x8 | \"Normal\" = 10x10 | \"Long\" = 15x15"; 
		String difficulty = "3 Difficulties : \"Easy\" | \"Normal\" | \"Hard\" ";
		String launch = "You can launch the game with :"
				+ "\n 	"
				+ "- Type Lenght Difficulty J1 J2"
				+ "\n 	For Example :"
				+ "\n 	Graphics"
				+ "\n 	Terminal Short Hard";
		
		String instruction = "This is a BattleShip'game, you can play this game with different time'lenght, "
				+ "difficulty, players and graphics interface."
				+ "\n" + type + "\n" + length + "\n" + difficulty +"\n" + launch;
		
		System.out.println(instruction);
	}

	public static void main(String[] args){

		BattleShip game = new BattleShip(Length.NORMAL,Difficulty.NORMAL,new Human(), new IA());

        if (args.length == 1 && "GUI".equals(args[0].toUpperCase())){
        	new GUI(game,WIDTH, HEIGHT);
			}
		else if (args.length >= 1){
			if ("TERMINAL".equals(args[0].toUpperCase())) {
				if (args.length >= 2){
					System.out.println(args[1]);
					game.setLenght(args[1]);
					if ( args.length >= 3 ){
						game.setDifficulty(args[2]);
					}
				}
				System.out.println(game.getLenght().getWidth());
				System.out.println(game.getDifficulty().getName());
				game.initGame();
				new UI(game);
			}
		}else {
			command();
			System.out.println(game.getLenght().getWidth());
			System.out.println(game.getDifficulty().getName());
			game.initGame();
			new UI(game);
		}


	}

}
