package view.terminal.controller;

import model.BattleShip;
import model.Case;

import java.util.Scanner;

public class Controller {
    /**
     * Scan only verify if values are parsable.
     * @param game to get player 2 ' case
     * @return a valid move
     */
    public static Case scan(BattleShip game){
        int len = game.getLenght().getWidth();
        Scanner scannner = new Scanner(System.in);
        Integer i = null;
        Integer j = null;
        String posI = "";
        String posJ = "";
        boolean bool;
        do{
            try{
                do {
                    System.out.print("Number : ");
                    posJ = scannner.next();
                }
                while(!scannner.hasNextInt());
                j = scannner.nextInt();

                scannner = new Scanner(System.in);
                do {
                    System.out.print("Letter : ");
                    posI = scannner.nextLine();
                }while (Case.toInt(posI, game) == null);
                i = Case.toInt(posI, game);


                if (i >= 0 && i<len && j >= 0 && j < len){
                    bool = false;
                }else {
                    bool = true;
                }
            }
            catch (Exception e){
                System.out.println("Error");
                bool = true;
            }
        }while (bool);


        return game.getCase(j,i,game.getPlayer2());
    }
}
