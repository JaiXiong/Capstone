package asset.world;

import java.awt.*;
import java.io.Serializable;

public class Floor implements Serializable {

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
        if (type.equals(TileObjects.TileType.TERRAIN.toString())) {
            return new Terrain(TileObjects.TileType.TERRAIN.toString(), color, null, ' ', row, col);
        } else if (type.equals(TileObjects.TileType.WALL.toString())) {
            return new Terrain(TileObjects.TileType.WALL.toString(), null, color, '#', row, col);
        } else if (type.equals(TileObjects.TileType.DOOR.toString())) {
            return new Terrain(TileObjects.TileType.DOOR.toString(), null, color, '+', row, col);
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
    public Terrain getTerrainAt(int r, int c) {
        return terrain[r][c];
    }

    //sets a terrain at location x y
    public void setTerrainAt(int r, int c, Terrain t) {
        terrain[r][c] = t;
    }

    //remove a terrain at location x y
    public void removeTerrainAt(int r, int c) {
        //to do still
        terrain[r][c] = null;
    }
    //returns the string type of the terrain
    public String getTerrainType(int r, int c) {
        return terrain[r][c].getType();
    }

    //checks to see if tile is a passable terrain
    public Boolean isTerrainPassableAt(int r, int c) {
        if (r < 0 || c < 0 || r >= getRows() || c >= getColumns())
            return false; //may not pass out of bounds
        //to do, I need to change  these to the standardize enum string??
        String item = terrain[r][c].getType();
        switch (item) {
            case "terrain": case "door":
                return true;
            case "wall":
                return false;
            default:
                throw new IllegalArgumentException("Unhandled terrain string: " + item);
        }
    }
}
