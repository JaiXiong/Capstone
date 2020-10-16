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


    /*
       Door starts here
     */
    boolean locked;
    //Code to unlock door, tied to the key. If they match, the door will open. An unlock code of -1 will work with any key.
    int unlockCode;

    /*
        Full constructor for door
     */
    public Terrain(String name, Color color, char symbol, int xSpot, int ySpot, boolean locked, int unlockCode) {
        super(name, color, symbol, xSpot, ySpot);
        this.locked = locked;
        this.unlockCode = unlockCode;
    }

    /*
        Simplified constructor for door
     */
    public Terrain(int xSpot, int ySpot, boolean locked) {
        super("Door", Color.GRAY, '+', xSpot, ySpot);
        this.locked = locked;
        this.unlockCode = -1;
    }

    public boolean getLocked() {return locked;}
    public int getUnlockCode() {return unlockCode;}

    public boolean unlock(int unlockCode) {
        if (this.unlockCode == unlockCode || this.unlockCode == -1 || unlockCode == -1) {
            this.locked = false;
            return true;
        }
        return false;
    }
}
