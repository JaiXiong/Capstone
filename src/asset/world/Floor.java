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

    //TODO update to allow for DOOR tiles to have different unlock codes paired to Key items
    //create the basic floor depending on type
    public Terrain makeFloor(String type, Color color, int row, int col) {
        if (type.equals(TileObjects.TileType.TERRAIN.toString())) {
            return new Terrain(TileObjects.TileType.TERRAIN.toString(), color, null, ' ', row, col);
        } else if (type.equals(TileObjects.TileType.WALL.toString())) {
            return new Terrain(TileObjects.TileType.WALL.toString(), null, color, '#', row, col);
        } else if (type.equals(TileObjects.TileType.DOOR.toString())) {
            //return new Terrain(TileObjects.TileType.DOOR.toString(), null, color, '+', row, col);
            return new Terrain(TileObjects.TileType.DOOR.toString(), color, '+', row, col, true, 200);

            //TODO need to replace all Color. with  color
        } else if (type.equals(TileObjects.TileType.TREE.toString())) {
            return new Terrain(TileObjects.TileType.TREE.toString(), null, Color.green, 'Ÿ', row, col);
        } else if (type.equals(TileObjects.TileType.TABLE.toString())) {
            return new Terrain(TileObjects.TileType.TABLE.toString(), null, Color.darkGray, '╥', row, col);
        } else if (type.equals(TileObjects.TileType.WATER.toString())) {
            return new Terrain(TileObjects.TileType.WATER.toString(), null, Color.blue, '≈', row, col);
        } else if (type.equals(TileObjects.TileType.TOILETRIGHT.toString())) {
            return new Terrain(TileObjects.TileType.TOILETRIGHT.toString(), null, Color.white, '╛', row, col);
        } else if (type.equals(TileObjects.TileType.TOILETLEFT.toString())) {
            return new Terrain(TileObjects.TileType.TOILETLEFT.toString(), null, Color.white, '╘', row, col);
        } else if (type.equals(TileObjects.TileType.LIGHTPOST1.toString())) {
            return new Terrain(TileObjects.TileType.LIGHTPOST1.toString(), null, Color.YELLOW, 'Î', row, col);
        } else if (type.equals(TileObjects.TileType.LIGHTPOST2.toString())) {
            return new Terrain(TileObjects.TileType.LIGHTPOST2.toString(), null, Color.YELLOW, '¶', row, col);
        } else if (type.equals(TileObjects.TileType.GRASS.toString())) {
            return new Terrain(TileObjects.TileType.GRASS.toString(), null, Color.GREEN, '▓', row, col);
        } else if (type.equals(TileObjects.TileType.CEMENTEDWALKWAY.toString())) {
            return new Terrain(TileObjects.TileType.CEMENTEDWALKWAY.toString(), null, Color.ORANGE, '▓', row, col);
        } else if (type.equals(TileObjects.TileType.CLOVERPLANT.toString())) {
            return new Terrain(TileObjects.TileType.CLOVERPLANT.toString(), null, Color.GREEN, '♣', row, col);
        } else if (type.equals(TileObjects.TileType.SPADEPLANT.toString())) {
            return new Terrain(TileObjects.TileType.SPADEPLANT.toString(), null, Color.ORANGE, '♠', row, col);
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
            case "terrain":
                return true;
            case "wall":
                return false;
            case "door":
                return (!terrain[r][c].getLocked());
            default:
                throw new IllegalArgumentException("Unhandled terrain string: " + item);
        }
    }
}
