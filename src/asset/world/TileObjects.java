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
        TERRAIN { public String toString() {return "terrain"; } }
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
