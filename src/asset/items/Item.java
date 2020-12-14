package asset.items;

import asset.AbstractGameAsset;
import console.ConsoleGlyph;

import java.awt.*;

public class Item extends AbstractGameAsset {

    private final String NAME;
    /* all EquipableItems have an itemID from 0-99, all others from 100-199
     * An item's itemID is for easily referencing items where it isn't player-facing,
     * generating random items, and linking an item to what that item does when used
     */
    private final int itemID;
    private final int pricetag;
    private final double P_VAL;
    private final double S_VAL;

    public Item(int id, String name, int price, double pVal, double sVal) {
        itemID = id;
        NAME = name;
        pricetag = price;
        P_VAL = pVal;
        S_VAL = sVal;
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
     * for IDs 0-99, calls GearTable to create an equipable item (currently 0-22 in use)
     * for IDs 100-199, creates a non-equippable item (currently 100-104 in use)
     * for invalid IDs, creates null
     */
    public static Item createItem(int itemID){
        if (itemID >= 0 && itemID < 100) return EquipableItem.createEquipment(itemID);
        switch (itemID) {
            case 100:
                return new Item(100, "Energy Drink", 4, 0, 20); //restore energy
            case 101:
                return new Item(101, "Big Energy Drink", 6, 0, 50); //restore energy
            case 102:
                return new Item(102, "'Juice' (21+)", 6, 60, -20); //cost energy, restore health
            case 103:
                return new Item(103, "Craft Dinner", 6, 30, 0); //restore health
            case 104:
                return new Item(104, "Some Kinda Tex-Mex?", 10, 70, 0); //restore health
            default:
                return null;
        }
    }

    public double getPrimaryValue() {
        return P_VAL;
    }

    public double getSecondaryValue() {
        return S_VAL;
    }

    /**
     * @return a color to display this item with.
     * EquipableItem should override this.
     */
    public Color getDisplayColor() {
        return Color.MAGENTA;
    }

    /**
     * @return a the display text for this item.
     */
    public String getDisplayText() {
        return NAME + "[HP " + (int)P_VAL + "](NRG " + (int)S_VAL + ")";
    }

    // Items currently can only exist in player inventory
    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(Color.BLACK, Color.WHITE, '?');
    }
}
