package engine;

import asset.character.AbstractCharacter;
import asset.world.Floor;
import asset.world.TileObjects;

import java.awt.*;
import java.util.ArrayList;

public class Gamestate {
    private static Gamestate instance = null;

    public static Gamestate getInstance() {
        if (instance == null) instance= new Gamestate();
        return instance;
    }

    private Floor floor;
    private ArrayList<AbstractCharacter> characters;

    private Gamestate() {
        //megahack - generate a floor to test display
        floor = new Floor(16, 16);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), Color.LIGHT_GRAY);
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                //orginal work
                if (i == 0 || j == 0 || i == 15 || j == 15) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }

                //Make a basic room with doors/walls
                if (j == 6 || j == 9) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                if (i == 5 || i == 10) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }

                if ((i == 5 && j == 7 || i == 5 && j == 8) || (i == 10 && j == 7 || i == 10 && j == 8)){
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, i, j));
                }

                if ((i == 0 && j == 7 || i == 0 && j == 8) || (i == 15 && j == 7 || i == 15 && j == 8)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, i, j));
                }

                if ((i == 3 || i == 8 || i == 12) && (j == 6 || j == 9)){
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, i, j));
                }
            }
        }

    }
    public Floor getFloor() {
        return floor;
    }

    public ArrayList<AbstractCharacter> getCharacters() {
        return characters;
    }
}
