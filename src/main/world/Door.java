package main.world;

import java.awt.*;

public class Door extends TileObjects {
    boolean locked;
    //Code to unlock door, tied to the key. If they match, the door will open. An unlock code of -1 will work with any key.
    int unlockCode;

    /*
        Full constructor.
     */
    public Door(String name, Color color, int xSpot, int ySpot, boolean locked, int unlockCode) {
        super(name, color, xSpot, ySpot);
        this.locked = locked;
        this.unlockCode = unlockCode;
    }

    /*
        Simplified constructor.
     */
    public Door(int xSpot, int ySpot, boolean locked) {
        super("Door", Color.GRAY, xSpot, ySpot);
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
