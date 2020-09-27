package main.world;

import java.awt.*;

public class TileObjects {
    int x;
    int y;
    Color color;
    String type;

    public TileObjects(String name, Color color, int xSpot, int ySpot) {
        this.x = xSpot;
        this.y = ySpot;
        this.type = name;
        this.color = color;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public Color getColor() {return this.color;}
    public String getType() {return this.type;}
}
