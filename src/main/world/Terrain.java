package main.world;

import java.awt.*;

public class Terrain extends TileObjects{
    //todo
    private Color backgroundColor;
    public Terrain(String name, Color color, int xSpot, int ySpot) {
        super(name, color, xSpot, ySpot);
    }

    public Color getBackgroundColor() {return this.backgroundColor;}

    @Override
    public String getType() {
        return this.type;
    }
}
