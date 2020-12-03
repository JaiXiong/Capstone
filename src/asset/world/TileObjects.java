package asset.world;

import console.ConsoleGlyph;
import asset.AbstractGameAsset;

import java.awt.*;

public class TileObjects extends AbstractGameAsset {
    int x;
    int y;
    Color color;
    char symbol;
    String type;

    /**
     *  Define possible Tile Types
     */
    public enum TileType {
        TERRAIN("terrain"),
        WALL("wall"),
        DOOR("door"),
        TREE("tree"),
        TABLE("table"),
        WATER("water"),
        TOILETRIGHT("toiletright"),
        TOILETLEFT("toiletleft"),
        LIGHTPOST1("lightpost1"),
        LIGHTPOST2("lightpost2"),
        GRASS("grass"),
        CEMENTEDWALKWAY("cementedwalkway"),
        CLOVERPLANT("cloverplant"),
        SPADEPLANT("spadeplant"),
        SOLIDWALL("solidwall"),
        DIVIDER("divider"),
        SHELF("shelf"),
        CHAIRLEFT("chairleft"),
        CHAIRRIGHT("chairright"),
        WINDOWCROSS("windowcross"),
        WINDOWHORIZONTALBAR("windowhoriztonalbar"),
        WINDOWVERTICALBAR("windowverticalcross"),
        WINDOWLEFTBOTTOMCORNERBAR("windowleftbottomcornercross"),
        WINDOWRIGHTBOTTOMCORNERBAR("windowrightbottomcornercross"),
        WINDOWLEFTTOPCORNERBAR("windowleftopcornercross"),
        WINDOWRIGHTTOPCORNERBAR("windowrighttopcornercross"),
        CHALKBOARD("chalkboard"),
        STAIRCASE("staircase"), // transition to the next level
        EMERGENCY_EXIT("emergency exit"); // transition to the hub

        private final String NAME;

        TileType(String name){
            NAME = name;
        }

        public String getName() {
            return NAME;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public TileObjects(String name, Color color, char symbol, int xSpot, int ySpot) {
        this.x = xSpot;
        this.y = ySpot;
        this.type = name;
        this.color = color;
        this.symbol = symbol;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public Color getColor() {return this.color;}
    public char getSymbol() {return this.symbol;}
    public String getType() {return this.type;}

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, color, symbol);
    }
}
