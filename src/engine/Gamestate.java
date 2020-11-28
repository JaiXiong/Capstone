package engine;

import asset.character.*;
import asset.world.Floor;
import asset.world.TileObjects;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Gamestate implements Serializable {
    private static Gamestate instance = null;

    private Floor floor;
    private ArrayList<AbstractCharacter> characters;
    /**
     * The difficulty level of the current floor.
     * This is NOT the game's difficulty setting - instead it corresponds to the floor's "depth" in the game.
     * In our current design, this should roughly correspond to:
     * -1 - ERROR(no current floor)
     * 0 - the dorm or hub area
     * 1 - freshman year, first semester
     * 2 - fresgman year, second semester
     * 3 - sophomore year, first semester
     * ...
     * 8 - senior year, second semester
     */
    private int floorDifficulty;

    private Gamestate() {
        returnToBase();
    }

    public void returnToBase() {
        generateAndPopulateFloor(floorDifficulty = 0);
    }

    public void nextFloor() {
        generateAndPopulateFloor(++floorDifficulty);
    }


    /**
     * Call this method to change the floor the player is on.
     */
    private void generateAndPopulateFloor(int depth) {
        if (depth < 0)
            throw new IllegalArgumentException("Depth may not be less than zero.");
        //PlayerCharacter playerCharacter = depth == 0 ? new PlayerCharacter() : getPlayerCharacter();
        PlayerCharacter playerCharacter = new PlayerCharacter(); //todo - remove this line and use the one above. For now, this lets us test depths other than 4(if we need to generate NPCs)
        Point pcLocation;
        Point bossLocation = null;
        switch (depth) {
            case 0:
                EMSCourtYard();
                pcLocation = new Point(floor.getColumns()/2, floor.getRows()/2); //todo - edit pcLocations if we want specific spawn points
                break;
            case 1:
                Room1();
                pcLocation = new Point(floor.getColumns()/2, floor.getRows()/2);
                break;
            case 2:
                Room2();
                pcLocation = new Point(1, 1);
                break;
            case 3:
                Bigroom3();
                pcLocation = new Point(floor.getColumns()/2, floor.getRows()/2);
                bossLocation = new Point(1, 1); //todo - where's a good place to put the boss?
                break;
            case 4:
                Bigroom4();
                pcLocation = new Point(floor.getColumns()/2, floor.getRows()/2);
                break;
            case 5:
                Bigroom5();
                pcLocation = new Point(floor.getColumns()/2, floor.getRows()/2);
                break;
            case 6:
                Bigroom6();
                pcLocation = new Point(floor.getColumns()/2, floor.getRows()/2);
                bossLocation = new Point(1, 1); //todo - where does this boss go?
                break;
            case 7:
                Rooftop();
                pcLocation = new Point(floor.getColumns()/2, floor.getRows()/2);
                break;
            default:
                throw new IllegalArgumentException("Depth " + depth + " has no associated floor pattern.");
        }
        playerCharacter.setLocation(pcLocation);
        characters = new ArrayList<>();
        characters.add(playerCharacter);
        populate(bossLocation);
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

    public int getFloorDifficulty() {
        return floorDifficulty;
    }

    private void Testroom() {
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

    //player main lobby
    private void EMSCourtYard() {
        floor = new Floor(35, 38);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), customColorMaker(customColor.DARKGREEN));
        for (int i = 0; i < 35; ++i) {
            for (int j = 0; j < 38; ++j) {
                //original work
                if (i == 0 || j == 0 || i == 34 || j == 37) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTGREEN), i, j));
                }
                //make EMS building
                if (i < 11) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), customColorMaker(customColor.MARBLEBLUE), i, j));
                }
                //Make the E in EMS
                if (i >= 1 && i <= 5) {
                    if ((i == 2 && j == 11) || i == 4 && j == 11) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), customColorMaker(customColor.GOLD), i, j));
                    }
                    if ((i == 1) && (j >= 11 && j <= 14)) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), customColorMaker(customColor.GOLD), i, j));
                    }
                    if ((i == 3) && (j >= 11 && j <= 14)) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), customColorMaker(customColor.GOLD), i, j));
                    }
                    if ((i == 5) && (j >= 11 && j <= 14)) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), customColorMaker(customColor.GOLD), i, j));
                    }
                }
                //make the M in EMS
                if (i >= 1 && i <= 5) {
                    if (j == 16 || j == 21) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), customColorMaker(customColor.GOLD), i, j));
                    }
                    if ((i == 2 && j == 17) || (i == 3 && j == 18) || (i == 3 && j == 19) || (i == 2 && j == 20)) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), customColorMaker(customColor.GOLD), i, j));
                    }
                }
                //make the S in EMS
                if (i >= 1 && i <= 5) {
                    if (((i == 1) && (j >= 23 && j <= 26)) || ((i == 3) && (j >= 23 && j <= 26)) || ((i == 5) && (j >= 23 && j <= 26))) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), customColorMaker(customColor.GOLD), i, j));
                    }
                    if ((i == 2 && j == 23) || (i == 4 && j == 26)) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), customColorMaker(customColor.GOLD), i, j));
                    }
                }
                //make the windows to the EMS building
                if (i == 2 || i == 3 || i == 7 || i == 8) {
                    if (j == 2 || j == 3 || j == 7 || j == 8 || j == 29 || j == 30 || j == 34 || j == 35) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWCROSS.toString(), customColorMaker(customColor.LIGHTBLUE), i, j));
                    }
                }
                //make top window bars for EMS windows
                if ((i == 1 || i == 6) && (j == 1 || j == 6 || j == 28 || j == 33)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWLEFTTOPCORNERBAR.toString(), Color.white, i, j));
                } else if ((i == 1 || i == 6) && (j == 4 || j == 9 || j == 31 || j == 36)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWRIGHTTOPCORNERBAR.toString(), Color.white, i, j));
                } else if ((i == 4 || i == 9) && (j == 1 || j == 6 || j == 28 || j == 33)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWLEFTBOTTOMCORNERBAR.toString(), Color.white, i, j));
                } else if ((i == 4 || i == 9) && (j == 4 || j == 9 || j == 31 || j == 36)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWRIGHTBOTTOMCORNERBAR.toString(), Color.white, i, j));
                } else if (((i == 1 || i == 4 || i == 6 || i == 9) && (j == 2 || j == 3 || j == 7 || j == 8 || j == 29 || j == 30 || j == 34 || j == 35))) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWHORIZONTALBAR.toString(), Color.white, i, j));
                } else if ((j == 1 || j == 4 || j == 6 || j == 9 || j == 28 || j == 31 || j == 33 || j == 36) && (i == 2 || i == 3 || i == 7 || i == 8)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWVERTICALBAR.toString(), Color.white, i, j));
                }
                //make door frames
                if ((i == 9) && (j == 13 || j == 23)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWLEFTTOPCORNERBAR.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                } else if ((i == 9) && (j == 15 || j == 25)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWRIGHTTOPCORNERBAR.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                } else if ((i == 10) && (j == 13 || j == 15 || j == 23 || j == 25)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWVERTICALBAR.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                } else if ((i == 9) && (j == 14 || j == 24)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWHORIZONTALBAR.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //2 staircases into the first level
                if ((i == 10) && (j == 14 || j == 24)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.STAIRCASE.toString(), customColorMaker(customColor.BROWN), i, j));
                }
                //pebble walk way
                if ((i >= 11 && i <= 33) && (j == 14 || j == 24)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CEMENTEDWALKWAY.toString(), customColorMaker(customColor.GOLD), i, j));
                }
                //pebble garden path
                if ((i == 12) && (j >= 25 && j <= 34)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CEMENTEDWALKWAY.toString(), customColorMaker(customColor.GOLD), i, j));
                } else if ((i == 29) && (j >= 25 && j <= 34)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CEMENTEDWALKWAY.toString(), customColorMaker(customColor.GOLD), i, j));
                } else if ((i >= 12 && i <= 29) && (j == 34)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CEMENTEDWALKWAY.toString(), customColorMaker(customColor.GOLD), i, j));
                }
                //TODO need to see if it's work refactoring background colors but grass doesn't look good with a black background, but it would be nice to have grass
                //grass
                /*if ((i >= 11 && i <= 33) && (j >= 1 && j <= 13 )) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.GRASS.toString(), customColorMaker(customColor.LIGHTGREEN), i, j));
                }*/

                //water pool
                if ((i >= 15 && i <= 25) && (j == 4 || j == 10)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWVERTICALBAR.toString(), Color.DARK_GRAY, i, j));
                } else if (i == 14 && j == 4) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWLEFTTOPCORNERBAR.toString(), Color.DARK_GRAY, i, j));
                } else if (i == 26 && j == 4) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWLEFTBOTTOMCORNERBAR.toString(), Color.DARK_GRAY, i, j));
                } else if (i == 14 && j == 10) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWRIGHTTOPCORNERBAR.toString(), Color.DARK_GRAY, i, j));
                } else if (i == 26 && j == 10) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWRIGHTBOTTOMCORNERBAR.toString(), Color.DARK_GRAY, i, j));
                } else if ((i == 14 || i == 26) && (j >= 4 && j <= 10)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WINDOWHORIZONTALBAR.toString(), Color.DARK_GRAY, i, j));
                } else {
                    //add water
                    if ((i >= 15 && i <= 25) && (j >= 5 && j <= 9)) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WATER.toString(), Color.CYAN, i, j));
                    }
                }
                //add left yard chairs
                if ((i == 15 || i == 16) && (j == 12 || j == 26)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CHAIRLEFT.toString(), customColorMaker(customColor.GOLD), i, j));
                } else if ((i == 20 || i == 21) && (j == 12 || j == 26)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CHAIRLEFT.toString(), customColorMaker(customColor.GOLD), i, j));
                } else if ((i == 25 || i == 26) && (j == 12 || j == 26)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CHAIRLEFT.toString(), customColorMaker(customColor.GOLD), i, j));
                }

                //add right yard chairs
                if ((i == 15 || i == 16) && (j == 26)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CHAIRRIGHT.toString(), customColorMaker(customColor.GOLD), i, j));
                } else if ((i == 20 || i == 21) && (j == 26)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CHAIRRIGHT.toString(), customColorMaker(customColor.GOLD), i, j));
                } else if ((i == 25 || i == 26) && (j == 26)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CHAIRRIGHT.toString(), customColorMaker(customColor.GOLD), i, j));
                }

                //add plants
                if ((i >= 14 && i <= 26) && (j == 29)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CLOVERPLANT.toString(), customColorMaker(customColor.GOLD), i, j));
                } else if ((i >= 14 && i <= 26) && (j == 30)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.TREE.toString(), customColorMaker(customColor.LIGHTRED), i, j));
                } else if ((i >= 14 && i <= 26) && (j == 31)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.TREE.toString(), Color.GREEN, i, j));
                } else if ((i >= 14 && i <= 26) && (j == 32)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SPADEPLANT.toString(), customColorMaker(customColor.PURPLE), i, j));
                }
            }
        }
    }

    private void Room1() {
        floor = new Floor(16, 16);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), customColorMaker(customColor.MARBLEBLUE));
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                //orginal work
                if (i == 0 || j == 0 || i == 15 || j == 15) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }

                //Make vertial walls
                if (j == 6 || j == 9) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //make horizontal walls
                if ((i == 5 || i == 10) && (j != 7 && j != 8)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }

                //these doors may be useless?? remove/confirm later
/*              if ((i == 5 && j == 7 || i == 5 && j == 8) || (i == 10 && j == 7 || i == 10 && j == 8)){
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), i, j));
                }

                if ((i == 0 && j == 7 || i == 0 && j == 8) || (i == 15 && j == 7 || i == 15 && j == 8)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), i, j));
                }*/

                //make doors
                if ((i == 3 || i == 8 || i == 12) && (j == 6 || j == 9)){
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), i, j));
                }
                //make staircase and emergency exit:
                floor.setTerrainAt(14, 7, floor.makeFloor(TileObjects.TileType.STAIRCASE.toString(), customColorMaker(customColor.BROWN), 14, 7));
                floor.setTerrainAt(14, 8, floor.makeFloor(TileObjects.TileType.EMERGENCY_EXIT.toString(), Color.WHITE, 14, 7));
            }
        }
    }

    private void Room2() {
        floor = new Floor(16, 16);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), customColorMaker(customColor.MARBLEBLUE));
        int m,n;
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (i == 0 || j == 0 || i == 15 || j == 15) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //create top 3 walls left to right
                if (i == 2 || i == 6 || i == 10) {
                    for (m = 0; m < 13; m++) {
                        System.out.println("i: "+i);
                        System.out.println("j: "+m);
                        floor.setTerrainAt(i, m, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, m));
                        System.out.println("Test 1: ");
                    }
                }
                //create bottom 3 walls right to left
                if (i == 4 || i == 8 || i == 12) {
                    for (n = 14; n > 1; n--) {
                        System.out.println("i: "+i);
                        System.out.println("j: "+n);
                        floor.setTerrainAt(i, n, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, n));
                        System.out.println("Test 2: ");
                    }
                }
            }
        }
        //create the room door
        floor.setTerrainAt(13, 5, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 13, 5));
        //create the walls to make room
        floor.setTerrainAt(14, 5, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.BROWN), 14, 5));
        //create the emergency exit:
        floor.setTerrainAt(1, 1, floor.makeFloor(TileObjects.TileType.EMERGENCY_EXIT.toString(), Color.WHITE, 1, 1));
        //create the staircase:
        floor.setTerrainAt(13, 14, floor.makeFloor(TileObjects.TileType.STAIRCASE.toString(), customColorMaker(customColor.BROWN), 13, 14));

    }

    private void Bigroom3() {
        floor = new Floor(24, 48);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), customColorMaker(customColor.MARBLEBLUE));
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 48; ++j) {
                //orginal work
                if (i == 0 || j == 0 || i == 23 || j == 47) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //make the eatery
                if (j == 30) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //make office
                if (i == 5 && j >= 11) {
                    if (j < 31) {
                        floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                    }
                }
                //make 2 left side classroom - split horizontal
                if (i == 11 && j < 12) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //make classroom wall vertical
                if (j == 11) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //make small pantry/closet vertical
                if ((i == 8) && (j >= 24 && j <= 30)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //make small pantry/closet horizontal
                if ((j == 24) && (i <= 8 && i >= 5)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
            }
        }
        //pantry/closet door
        floor.setTerrainAt(7, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 7, 24));
        //north classroom door
        floor.setTerrainAt(5, 18, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 5, 20));
        //northwest classroom door
        floor.setTerrainAt(9, 11, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 9, 11));
        //southwest classroom door
        floor.setTerrainAt(13, 11, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 13, 11));
        //eatery
        floor.setTerrainAt(13, 30, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 13, 30));
    }

    private void Bigroom4() {
        floor = new Floor(24, 48);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), customColorMaker(customColor.MARBLEBLUE));
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 48; ++j) {
                //Make the perimeter.
                if (i == 0 || j == 0 || i == 23 || j == 47) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Make vertical division.
                if (j == 16) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Make horizontal division.
                if (i == 8) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Make rooms on right side.
                if (i > 8 && j == 38) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Divide rooms on right side.
                if (i == 15 && j > 29) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Finish bottom room.
                if (i > 14 && j == 29) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Create bottom left closet.
                if (i == 18 && j < 7) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                if (i > 17 && j == 7) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Create top center closet.
                if (i == 4 && j > 22 && j < 40) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                if (i < 5 && j == 22) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                if (i < 5 && j == 40) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                if (i == 21 && j == 5) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.STAIRCASE.toString(), Color.WHITE, i , j));
                }
                if (i == 12 && j == 24) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.EMERGENCY_EXIT.toString(), Color.WHITE, i , j));
                }
            }
        }
        //Doors in horizontal divider.
        floor.setTerrainAt(8, 42, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 8, 42));
        floor.setTerrainAt(8, 23, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 8, 23));
        floor.setTerrainAt(8, 8, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 8, 8));
        //Doors in vertical divider.
        floor.setTerrainAt(14, 16, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 14, 16));
        floor.setTerrainAt(5, 16, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 5, 16));
        //Doors for right side rooms.
        floor.setTerrainAt(15, 33, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 15, 33));
        floor.setTerrainAt(15, 42, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 15, 42));
        floor.setTerrainAt(11, 38, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 11, 38));
        floor.setTerrainAt(19, 38, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 19, 38));
        //Door in bottom left closet.
        floor.setTerrainAt(20, 7, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 20, 7));
        //Doors in top center closet.
        floor.setTerrainAt(2, 22, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 2, 22));
        floor.setTerrainAt(2, 40, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 2, 40));
        //Tables in bottom.
        for(int i = 20; i < 26; ++i) {
            floor.setTerrainAt(16, i, floor.makeFloor(TileObjects.TileType.TABLE.toString(), Color.ORANGE, 16, i));
            floor.setTerrainAt(18, i, floor.makeFloor(TileObjects.TileType.TABLE.toString(), Color.ORANGE, 18, i));
            floor.setTerrainAt(20, i, floor.makeFloor(TileObjects.TileType.TABLE.toString(), Color.ORANGE, 20, i));
        }
        //Dividers on left side.
        for(int i = 11; i < 16; ++i) {
            floor.setTerrainAt(i, 8, floor.makeFloor(TileObjects.TileType.DIVIDER.toString(), Color.WHITE, i, 8));
        }
        //Shelves in bottom right closets.
        for(int i = 30; i < 38; ++i) {
            floor.setTerrainAt(22, i, floor.makeFloor(TileObjects.TileType.SHELF.toString(), Color.BLUE, 22, i));
        }
        for(int i = 17; i < 22; ++i) {
            floor.setTerrainAt(i, 44, floor.makeFloor(TileObjects.TileType.SHELF.toString(), Color.BLUE, i, 44));
        }
        for(int i = 9; i < 15; ++i) {
            floor.setTerrainAt(i, 46, floor.makeFloor(TileObjects.TileType.SHELF.toString(), Color.BLUE, i, 46));
        }
        //Chairs in top left room.
        floor.setTerrainAt(2, 3, floor.makeFloor(TileObjects.TileType.CHAIRLEFT.toString(), Color.LIGHT_GRAY, 2, 3));
        floor.setTerrainAt(3, 3, floor.makeFloor(TileObjects.TileType.CHAIRLEFT.toString(), Color.LIGHT_GRAY, 3, 3));
        floor.setTerrainAt(5, 3, floor.makeFloor(TileObjects.TileType.CHAIRLEFT.toString(), Color.LIGHT_GRAY, 5, 3));
        floor.setTerrainAt(6, 3, floor.makeFloor(TileObjects.TileType.CHAIRLEFT.toString(), Color.LIGHT_GRAY, 6, 3));
    }

    private void Bigroom5() {
        floor = new Floor(24, 48);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), customColorMaker(customColor.MARBLEBLUE));
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 48; ++j) {
                //Make the perimeter.
                if (i == 0 || j == 0 || i == 23 || j == 47) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Make the center room.
                if (((i == 5 || i == 18) && j > 6 && j < 41) || ((j == 7 || j == 40) && i > 4 && i < 19)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Make top left closet.
                if (i > 5 && i < 11 && j == 15) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                if (i == 11 && j > 6 && j < 16) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Make bottom right closet.
                if (i > 12  && i < 19 && j == 32) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                if (i == 12 && j > 31 && j < 40) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //Make center room dividers.
                if (i > 5 && i < 18 && (j == 19 || j == 28)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
            }
        }
        //Doors in center room.
        floor.setTerrainAt(5, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 5, 24));
        floor.setTerrainAt(18, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 18, 24));
        floor.setTerrainAt(14, 7, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 14, 7));
        floor.setTerrainAt(8, 40, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 8, 40));
        //Door for top left closet.
        floor.setTerrainAt(11, 11, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 11, 11));
        //Door for bottom right closet.
        floor.setTerrainAt(12, 36, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 12, 36));
        //Doors for divider.
        floor.setTerrainAt(14, 19, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 18, 19));
        floor.setTerrainAt(8, 28, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 8, 28));
    }

    private void Bigroom6() {
        floor = new Floor(24, 48);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), customColorMaker(customColor.MARBLEBLUE));
        int m,n;
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 48; ++j) {
                if (i == 0 || j == 0 || i == 23 || j == 47) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //make left wall
                if (j == 6) {
                    floor.setTerrainAt(i, 6, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, 6));
                }
                //make right wall
                if (j == 42) {
                    floor.setTerrainAt(i, 42, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, 42));
                }
                //make top wall
                if (i == 6) {
                    floor.setTerrainAt(6, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), 6, j));
                }

                //make the central office desk

                //left side of desk
                if (j == 12 && (i >= 12 && i <= 18)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //right side of desk
                if (j == 36 && (i >= 12 && i <= 18)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //front of desk
                if (i == 18 && (j >= 12 && j <= 36)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }

                //back left of desk
                if (i == 12 && (j >= 12 && j <= 22)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //back right of desk
                if (i == 12 && (j >= 26 && j <= 36)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }

                //make doors

                //dept chair door (right)
                floor.setTerrainAt(18, 42, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 18, 42));
                //dept char closet
                floor.setTerrainAt(6, 45, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 6, 45));
                //assist dean door (left)
                floor.setTerrainAt(18, 6, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 18, 2));
                //assist dean closet
                floor.setTerrainAt(6, 2, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 6, 2));
                //dean's door
                floor.setTerrainAt(6, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 6, 24));
                //entrance
                floor.setTerrainAt(23, 24, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 23, 24));
            }
        }
    }

    private void Rooftop() {
        //rooftop room
        floor = new Floor(20, 20);
        floor.fillAll(TileObjects.TileType.TERRAIN.toString(), customColorMaker(customColor.MARBLEBLUE));
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                if (i == 0 || j == 0 || i == 19 || j == 19) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBROWN), i, j));
                }
                //make North east stairwell
                //entrance and exit???
                if ((i >= 2 && i <= 6) && (j >= 2 && j <= 6  )) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SOLIDWALL.toString(), Color.LIGHT_GRAY, i, j));
                }
                //add door for stair well
                floor.setTerrainAt(6, 4, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 6, 4));

                //make EMS greenhouse (is there even on up there? has to be one right?)

                //greenhouse back wall
                if (i == 17 && (j >= 2 && j <= 17)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBLUE), i, j));
                }
                //greenhouse front wall
                if (i == 12 && (j >=2 && j <=17 )) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBLUE), i, j));
                }
                //right walls of greenhouse
                if ((i >= 12 && i <= 17) && (j == 2 || j == 17)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.WALL.toString(), customColorMaker(customColor.LIGHTBLUE), i, j));
                }
                //add greenhouse door
                floor.setTerrainAt(12, 4, floor.makeFloor(TileObjects.TileType.DOOR.toString(), customColorMaker(customColor.BROWN), 5, 12));

                //add plants in greenhouse
                if ((i >=14 && i <=15) && (j >= 4 && j <=15)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.TREE.toString(), Color.green, i, j));
                }

                //add some lights to the roof top
                if ((i == 1 || i == 4 || i == 7 || i == 10) && (j == 18)) {
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.LIGHTPOST1.toString(), Color.yellow, i, j));
                }

                //add clover plants
                if ((i == 2 || i == 6) && (j >=9 && j <=15)){
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.CLOVERPLANT.toString(), Color.green, i, j));
                }
                //add spade plants
                if ((i == 4) && (j >=9 && j <=15)){
                    floor.setTerrainAt(i, j, floor.makeFloor(TileObjects.TileType.SPADEPLANT.toString(), Color.ORANGE, i, j));
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
    public PlayerCharacter getPlayerCharacter() {
        AbstractCharacter playerCharacter = getCharacters().get(0);
        if (playerCharacter instanceof PlayerCharacter)
            return (PlayerCharacter)playerCharacter;
        throw new IllegalStateException("Character at index 0 was not a player character.");
    }

    public AbstractCharacter getCharacterAt(int row, int column) {
        for (AbstractCharacter character : characters) {
            Point location = character.getLocation();
            if (location.x == column && location.y == row) {
                return character;
            }
        }
        return null;
    }
    private void populate(Point bossLocation) {
        //generate and place a boss if appropriate for this floor:
        if (bossLocation != null) {
            AbstractNonPlayerCharacter boss = NPCFactory.npcLookup((floorDifficulty / 3) + 10);
            boss.setLocation(bossLocation);
            characters.add(boss);
            return;
        }
        //otherwise generate non-boss NPCs:
        int npcCount = 2 * floorDifficulty + RNG.get().nextInt((int)Math.pow(floorDifficulty, 2.0) + 1);
        int attemptCount = 0;
        boolean placed = false;
        while (npcCount > 0) {
            do {
                //paranoia - don't let us get stuck in an infinite loop if we can't place all the npcs we want.
                if (++attemptCount > 1_024) {
                    npcCount = 0;
                    break;
                }
                int c = RNG.get().nextInt(floor.getColumns());
                int r = RNG.get().nextInt(floor.getRows());
                if (floor.isTerrainPassableAt(r, c) && getCharacterAt(r, c) == null) {
                    AbstractNonPlayerCharacter npc = NPCFactory.randomNPC(floorDifficulty);
                    npc.setLocation(new Point(c, r));
                    if ((RNG.get().nextInt(6) + floorDifficulty - 7) > 0) {
                        //how many times to upgrade, trending up with floor level
                        int upgrades = (int) ((Math.random() * (floorDifficulty / 3)) + 1);
                        for (int j = 0; j < upgrades; j++) {
                            npc.upgrade();
                        }
                    }
                    characters.add(npc);
                    placed = true;
                }
            } while (!placed);
            --npcCount;
            placed = false;
        }
    }

    //Create a static list of custom Colors, can be expanded.
    public enum customColor {
        BROWN, GOLD, PURPLE, DARKGREEN, LIGHTGREEN, LIGHTBROWN, LIGHTBLUE, LIGHTRED, VERYDARKRED, MARBLEBLUE;
    }

    //Make the custom color
    public Color customColorMaker(customColor color) {
        switch (color) {
            case BROWN:
                return new Color(102, 51, 0);
                //return new Color(51, 0, 0);
            case GOLD:
                return new Color(255, 204, 51);
            case PURPLE:
                return new Color(229, 204, 255);
            case DARKGREEN:
                return new Color(0, 102, 0);
            case LIGHTGREEN:
                return new Color(0, 255, 51);
            case LIGHTBROWN:
                return new Color(163, 112, 0);
            case LIGHTBLUE:
                return new Color(51, 204, 255);
            case LIGHTRED:
                return new Color(255, 51, 51);
            case VERYDARKRED:
                return new Color(153, 0, 0);
            case MARBLEBLUE:
                return new Color(0, 25, 51);
        }
        throw new NoSuchElementException();
    }

    public Color getCustomRGB(int r, int g, int b) {
        Color color = new Color(r, g, b);
        return color;
    }
}
