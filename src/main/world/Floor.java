package main.world;

import java.awt.*;

public class Floor{

    //The size of the floor
    private int ROWS;
    private int COLUMNS;

    //The terrain to be created
    private Terrain[][] terrain;

    //Floor constructor
    public Floor(int rows, int columns) {
        this.ROWS = rows;
        this.COLUMNS = columns;
        this.terrain = new Terrain[ROWS][COLUMNS];
    }

    //create the basic floor depending on type
    public Terrain makeFloor(String type, Color color, int row, int col) {
        if (type == "terrain") {
            return new Terrain("terrain", color, row, col);
        } else if (type == "wall") {
            return new Terrain("wall", color, row, col);
        } else if (type == "door") {
            return new Terrain("door", color, row, col);
        } else {
            return null;
        }
    }

    public void fillAll(String type, Color color) {
        for(int x = 0; x < ROWS; x++) {
            for(int y = 0; y < COLUMNS; y++) {
                terrain[ROWS][COLUMNS] = makeFloor(type, color, x, y);
            }
        }
    }

}
