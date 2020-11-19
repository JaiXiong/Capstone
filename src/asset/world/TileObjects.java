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
        //override  toString() for enum, is there a better way?
        WALL { public String toString() { return "wall"; } },
        DOOR { public String toString() { return "door"; } },
        TERRAIN { public String toString() {return "terrain"; } },
        TREE { public String toString() {return "tree";}},
        TABLE { public String toString() {return "table";}},
        WATER { public String toString() {return "water";}},
        TOILETRIGHT { public String toString() {return "toiletright";}},
        TOILETLEFT { public String toString() {return "toiletleft";}},
        LIGHTPOST1 { public String toString() {return "lightpost1";}},
        LIGHTPOST2 { public String toString() {return "lightpost2";}},
        GRASS { public String toString() {return "grass";}},
        CEMENTEDWALKWAY { public String toString() {return "cementedwalkway";}},
        CLOVERPLANT { public String toString() {return "cloverplant";}},
        SPADEPLANT{ public String toString() {return "spadeplant";}},
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
