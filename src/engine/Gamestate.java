package engine;

import asset.character.AbstractCharacter;
import asset.character.PlayerCharacter;
import asset.world.Floor;
import asset.world.TileObjects;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Gamestate implements Serializable {
    private static Gamestate instance = null;

    private Floor floor;
    private ArrayList<AbstractCharacter> characters;

    private Gamestate() {
        //megahack - generate a floor to test display
        //Testroom();
        //Room1();
        //Room2();
        //Bigroom3();
        //Bigroom4();
        Bigroom5();
        characters = new ArrayList<>();
        PlayerCharacter playerCharacter = new PlayerCharacter();
        playerCharacter.setLocation(new Point(floor.getColumns()/2, floor.getRows()/2));
        characters.add(playerCharacter);
    }

    public static void clearInstance() {
        instance = null;
    }

    public static Gamestate getInstance() {
        if (instance == null) instance= new Gamestate();
        return instance;
    }

    public static void loadInstance(Gamestate gamestate) {
        instance = gamestate;
    }

    public void Testroom() {
        floor = new Floor(16, 16);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), Color.LIGHT_GRAY);
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                //orginal work
                if (i == 0 || j == 0 || i == 15 || j == 15) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
            }
        }
    }

    public void Room1() {
        floor = new Floor(16, 16);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), Color.LIGHT_GRAY);
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                //orginal work
                if (i == 0 || j == 0 || i == 15 || j == 15) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }

                //Make a basic room with doors/walls on both sides
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

    public void Room2() {
        floor = new Floor(16, 16);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), Color.LIGHT_GRAY);
        int m,n;
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (i == 0 || j == 0 || i == 15 || j == 15) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //create top 3 walls left to right
                if (i == 2 || i == 6 || i == 10) {
                    for (m = 0; m < 13; m++) {
                        System.out.println("i: "+i);
                        System.out.println("j: "+m);
                        floor.setTerrainAt(i, m, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, m));
                        System.out.println("Test 1: ");
                    }
                }
                //create bottom 3 walls right to left
                if (i == 4 || i == 8 || i == 12) {
                    for (n = 14; n > 1; n--) {
                        System.out.println("i: "+i);
                        System.out.println("j: "+n);
                        floor.setTerrainAt(i, n, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, n));
                        System.out.println("Test 2: ");
                    }
                }
            }
        }
        //create the room door
        floor.setTerrainAt(13, 5, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 13, 5));
        //create the walls to make room
        floor.setTerrainAt(14, 5, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, 14, 5));
    }

    public void Bigroom3() {
        floor = new Floor(24, 48);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), Color.LIGHT_GRAY);
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 48; ++j) {
                //orginal work
                if (i == 0 || j == 0 || i == 23 || j == 47) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //make the eatery
                if (j == 30) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //make office
                if (i == 5 && j >= 11) {
                    if (j < 31) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                    }
                }
                //make 2 left side classroom - split horizontal
                if (i == 11 && j < 12) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //make classroom wall vertical
                if (j == 11) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //make small pantry/closet vertical
                if ((i == 8) && (j >= 24 && j <= 30)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //make small pantry/closet horizontal
                if ((j == 24) && (i <= 8 && i >= 5)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
            }
        }
        //pantry/closet door
        floor.setTerrainAt(7, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 7, 24));
        //north classroom door
        floor.setTerrainAt(5, 18, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 5, 20));
        //northwest classroom door
        floor.setTerrainAt(9, 11, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 9, 11));
        //southwest classroom door
        floor.setTerrainAt(13, 11, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 13, 11));
        //eatery
        floor.setTerrainAt(13, 30, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 13, 30));
    }

    public void Bigroom4() {
        floor = new Floor(24, 48);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), Color.LIGHT_GRAY);
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 48; ++j) {
                //Make the perimeter.
                if (i == 0 || j == 0 || i == 23 || j == 47) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Make vertical divider.
                if (j == 16) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Make horizontal divider.
                if (i == 8) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Make rooms on right side.
                if (i > 8 && j == 38) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Divide rooms on right side.
                if (i == 15 && j > 29) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Finish bottom room.
                if (i > 14 && j == 29) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Create bottom left closet.
                if (i == 18 && j < 7) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                if (i > 17 && j == 7) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Create top center closet.
                if (i == 4 && j > 22 && j < 40) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                if (i < 5 && j == 22) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                if (i < 5 && j == 40) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
            }
        }
        //Doors in horizontal divider.
        floor.setTerrainAt(8, 42, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 8, 42));
        floor.setTerrainAt(8, 23, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 8, 23));
        floor.setTerrainAt(8, 8, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 8, 8));
        //Doors in vertical divider.
        floor.setTerrainAt(14, 16, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 14, 16));
        floor.setTerrainAt(5, 16, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 5, 16));
        //Doors for right side rooms.
        floor.setTerrainAt(15, 33, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 15, 33));
        floor.setTerrainAt(15, 42, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 15, 42));
        floor.setTerrainAt(11, 38, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 11, 38));
        floor.setTerrainAt(19, 38, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 19, 38));
        //Door in bottom left closet.
        floor.setTerrainAt(20, 7, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 20, 7));
        //Doors in top center closet.
        floor.setTerrainAt(2, 22, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 2, 22));
        floor.setTerrainAt(2, 40, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 2, 40));
    }

    public void Bigroom5() {
        floor = new Floor(24, 48);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), Color.LIGHT_GRAY);
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 48; ++j) {
                //Make the perimeter.
                if (i == 0 || j == 0 || i == 23 || j == 47) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Make the center room.
                if (((i == 5 || i == 18) && j > 6 && j < 41) || ((j == 7 || j == 40) && i > 4 && i < 19)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Make top left closet.
                if (i > 5 && i < 11 && j == 15) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                if (i == 11 && j > 6 && j < 16) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Make bottom right closet.
                if (i > 12  && i < 19 && j == 32) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                if (i == 12 && j > 31 && j < 40) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //Make center room dividers.
                if (i > 5 && i < 18 && (j == 19 || j == 28)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
            }
        }
        //Doors in center room.
        floor.setTerrainAt(5, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 5, 24));
        floor.setTerrainAt(18, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 18, 24));
        floor.setTerrainAt(14, 7, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 14, 7));
        floor.setTerrainAt(8, 40, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 8, 40));
        //Door for top left closet.
        floor.setTerrainAt(11, 11, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 11, 11));
        //Door for bottom right closet.
        floor.setTerrainAt(12, 36, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 12, 36));
        //Doors for divider.
        floor.setTerrainAt(14, 19, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 18, 19));
        floor.setTerrainAt(8, 28, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 8, 28));
    }

    public void Bigroom6() {
        floor = new Floor(24, 48);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), Color.LIGHT_GRAY);
        int m,n;
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 48; ++j) {
                if (i == 0 || j == 0 || i == 23 || j == 47) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //make left wall
                if (j == 6) {
                    floor.setTerrainAt(i, 6, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, 6));
                }
                //make right wall
                if (j == 42) {
                    floor.setTerrainAt(i, 42, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, 42));
                }
                //make top wall
                if (i == 6) {
                    floor.setTerrainAt(6, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, 6, j));
                }

                //make the central office desk

                //left side of desk
                if (j == 12 && (i >= 12 && i <= 18)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //right side of desk
                if (j == 36 && (i >= 12 && i <= 18)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //front of desk
                if (i == 18 && (j >= 12 && j <= 36)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }

                //back left of desk
                if (i == 12 && (j >= 12 && j <= 22)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //back right of desk
                if (i == 12 && (j >= 26 && j <= 36)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }

                //make doors

                //dept chair door (right)
                floor.setTerrainAt(18, 42, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 18, 42));
                //dept char closet
                floor.setTerrainAt(6, 45, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 6, 45));
                //assist dean door (left)
                floor.setTerrainAt(18, 6, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 18, 2));
                //assist dean closet
                floor.setTerrainAt(6, 2, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 6, 2));
                //dean's door
                floor.setTerrainAt(6, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 6, 24));
                //entrance
                floor.setTerrainAt(23, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 23, 24));
            }
        }
    }

    public void Rooftop() {
        //rooftop room
        floor = new Floor(20, 20);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), Color.LIGHT_GRAY);
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                if (i == 0 || j == 0 || i == 19 || j == 19) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //make North east stairwell
                //entrance and exit???
                if ((i >= 2 && i <= 6) && (j >= 2 && j <= 6  )) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //add door for stair well
                floor.setTerrainAt(6, 4, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 6, 4));

                //make EMS greenhouse (is there even on up there? has to be one right?)

                //greenhouse back wall
                if (i == 17 && (j >= 2 && j <= 17)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //greenhouse front wall
                if (i == 12 && (j >=2 && j <=17 )) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //right walls of greenhouse
                if ((i >= 12 && i <= 17) && (j == 2 || j == 17)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), Color.DARK_GRAY, i, j));
                }
                //add greenhouse door
                floor.setTerrainAt(12, 4, floor.makeFloor(TileObjects.TileType.DOOR.toString(), Color.DARK_GRAY, 5, 12));
            }
        }
    }
    public Floor getFloor() {
        return floor;
    }

    public ArrayList<AbstractCharacter> getCharacters() {
        return characters;
    }
    public PlayerCharacter getPlayerCharacter() {
        AbstractCharacter playerCharacter = getCharacters().get(0);
        if (playerCharacter instanceof PlayerCharacter)
            return (PlayerCharacter)playerCharacter;
        throw new IllegalStateException("Character at index 0 was not a player character.");
    }
}
