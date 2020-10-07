package main.items;

import main.world.TileObjects;

import java.awt.*;

public class Key extends Item {
    //Code to unlock door. If the codes match, the door will open. An unlock code of -1 will unlock any door.
    int unlockCode;

    public Key(int id, String name, int price, int unlockCode) {
        super(id, name, price);
        this.unlockCode = unlockCode;
    }

    public int getUnlockCode() {return unlockCode;}
}
