package model.utils;

import java.util.ArrayList;
import java.util.Arrays;

public enum Difficulty {
    EASY("Easy",new ArrayList<Integer>(Arrays.asList(5,5,4,4,3)),new ArrayList<String>(Arrays.asList("Porte-avions 1","Porte-avions 2","Croiseur 1", "Croiseur 2", "Contre-Torpilleur"))),
    NORMAL("Normal",  new ArrayList<Integer>(Arrays.asList(5,4,3,3,2)), new ArrayList<String>(Arrays.asList("Porte-avions","Croiseur","Contre-Torpilleur","Sous-Marin","Torpilleur"))),
    HARD("Hard", new ArrayList<Integer>(Arrays.asList(5,4,3,3,2,2)),new ArrayList<String>(Arrays.asList("Porte-avions","Croiseur","Contre-Torpilleur","Sous-Marin","Torpilleur 1","Torpilleur 2")));

    private String name;
    private ArrayList<Integer> ship;
    private ArrayList<String> shipName;


    Difficulty(String name, ArrayList<Integer> shipSize, ArrayList<String> shipName) {
        this.name = name;
        this.ship = shipSize;
        this.shipName = shipName;
    }

    /**
     *
     * @return the difficulty'Name
     */
    public String getName() {
        return this.name;
    }


    /**
     *
     * @param index
     * @return The ship'name corresponding to index number in the ship'list
     */
    public String getName(int index) {
        if (index < this.shipName.size()) {
            return this.shipName.get(index);
        }
        else {
            return null;
        }
    }

    /**
     *
     * @return The corresponding Ship'length
     */
    public ArrayList<Integer> getShips() {
        return this.ship;
    }

    /**
     * Get Difficulty from a string
     * @param obj If OBJ is equal to a selected string return the corresponding enum
     * @return Difficulty EASY/NORMAL/HARD
     */
    public static Difficulty getDifficulty(String obj) {
        obj = obj.toUpperCase();
        if ("EASY".equals(obj)) {
            return Difficulty.EASY;
        }
        if("NORMAL".equals(obj)) {
            return Difficulty.NORMAL;
        }
        if("HARD".equals(obj)) {
            return Difficulty.HARD;
        }
        return null;
    }
}