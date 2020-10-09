package asset.world;

import console.ConsoleGlyph;

import java.awt.*;

public class Terrain extends TileObjects{
    //todo
    private Color backgroundColor;
    public Terrain(String name, Color bg, Color fg, char symbol, int xSpot, int ySpot) {
        super(name, fg, symbol, xSpot, ySpot);
        backgroundColor = bg;
    }

    public Color getBackgroundColor() {return this.backgroundColor;}

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(getBackgroundColor(), getColor(), getSymbol());
    }
}
