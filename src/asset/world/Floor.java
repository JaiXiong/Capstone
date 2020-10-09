package asset.world;

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
            return new Terrain("terrain", color, null, ' ', row, col);
        } else if (type == "wall") {
            return new Terrain("wall", null, color, '#', row, col);
        } else if (type == "door") {
            return new Terrain("door", null, color, '+', row, col);
        } else {
            return null;
        }
    }

    public void fillAll(String type, Color color) {
        for(int x = 0; x < ROWS; x++) {
            for(int y = 0; y < COLUMNS; y++) {
                terrain[x][y] = makeFloor(type, color, x, y);
            }
        }
    }


    public int getColumns() {
        return COLUMNS;
    }

    public int getRows() {
        return ROWS;
    }

    //returns a terrain
    public Terrain getTerrainAt(int x, int y) {
        return terrain[x][y];
    }

    public void setTerrainAt(int x, int y, Terrain t) {
        terrain[x][y] = t;
    }
    //returns the string type of the terrain
    public String getTerrainType(int x, int y) {
        return terrain[x][y].getType();
    }

    //checks to see if tile is a passable terrain
    public Boolean isTerrainPassableAt(int x, int y) {
        String item = terrain[x][y].getType();
        switch (item) {
            case "wall": case "terrain": case "door":
                return true;
            default:
                return false;
        }
    }
}
