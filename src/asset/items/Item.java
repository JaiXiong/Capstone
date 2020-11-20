package asset.items;

import asset.AbstractGameAsset;
import console.ConsoleGlyph;

import java.awt.*;

public class Item extends AbstractGameAsset {
    //todo - define additional item properties not specific to implementations, as needed

    private final String NAME;
    /* all EquipableItems have an itemID from 0-99, all others from 100-199
     * An item's itemID is for easily referencing items where it isn't player-facing,
     * generating random items, and linking an item to what that item does when used
     */
    int itemID;
    int pricetag;

    public Item(int id, String name, int price) {
        itemID = id;
        NAME = name;
        pricetag = price;
    }

    public String getName() {
        return NAME;
    }

    public int getItemID() {
        return itemID;
    }

    public int getPricetag() {
        return pricetag;
    }

    /* @param itemID of item to create
     * for IDs 0-99, calls GearTable to create an equipable item
     * for IDs 100-199, creates a non-equippable item
     * for IDs 200-299 and -1, creates a key with that itemID, keys use itemID as unlockCode
     * for invalid IDs, creates null
     */
    public static Item createItem(int itemID){
        if (itemID >= 0 && itemID < 100) return EquipableItem.createEquipment(itemID);
        if (itemID == -1 || (itemID >= 200 && itemID < 300)) return new Item(itemID, "Key", 1);
        switch (itemID) {
            case 100:
                return new Item(100, "Energy Drink", 4); //restore energy
            case 101:
                return new Item(101, "Big Energy Drink", 6); //restore energy
            case 102:
                return new Item(102, "'Juice' (21+)", 6); //cost energy, restore health
            case 103:
                return new Item(103, "Craft Dinner", 6); //restore health
            case 104:
                return new Item(104, "Some Kinda Tex-Mex?", 10); //restore health
            default:
                return null;
        }
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(Color.BLACK, Color.WHITE, '?'); //todo - field set by constructor
    }
}
