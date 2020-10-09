package engine;

import asset.world.Floor;

import java.awt.*;

public class Gamestate {
    private static Gamestate instance = null;

    public static Gamestate getInstance() {
        if (instance == null) instance= new Gamestate();
        return instance;
    }

    private Floor floor;
    //todo - actor list?

    private Gamestate() {
        //megahack - generate a floor to test display
        floor = new Floor(16, 16);
        floor.fillAll("terrain", Color.LIGHT_GRAY);
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (i == 0 || j == 0 || i == 15 || j == 15)
                    floor.setTerrainAt(i, j, floor.makeFloor("wall", Color.DARK_GRAY, i, j));
            }
        }
    }

    public Floor getFloor() {
        return floor;
    }
}
