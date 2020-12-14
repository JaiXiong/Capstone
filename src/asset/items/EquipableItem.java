package asset.items;

import java.awt.*;

/**
 * Define items which can be equipped as gear.
 */
public class EquipableItem extends Item {
    public enum Slot {
        OFFENSE_A,
        OFFENSE_B,
        DEFENSE_A,
        DEFENSE_B
    }
    private final Slot SLOT;

    public EquipableItem(int id, Slot slot, String name, int price, double baseStat,
        double toHitModifier) {

        super(id, name, price, baseStat, toHitModifier);
        SLOT = slot;
    }

    public Slot getSlot() {
        return SLOT;
    }

    public double getToHitModifier() {
        return getSecondaryValue();
    }

    public double getBaseStat() { return getPrimaryValue(); }

    public static EquipableItem createEquipment(int itemID){
        switch (itemID) {
            case 0:
                return new EquipableItem(0, Slot.OFFENSE_A, "Stick", 0, 10.0, 0.0);
            case 1:
                return new EquipableItem(1, Slot.OFFENSE_A, "Unsharpened Pencil", 2, 13.0, 0.02);
            case 2:
                return new EquipableItem(2, Slot.OFFENSE_A, "Fountain Pen", 7, 17.0, 0.02);
            case 3:
                return new EquipableItem(3, Slot.OFFENSE_A, "'Cool' Guitar", 16, 20.0, 0.05);
            case 4:
                return new EquipableItem(4, Slot.OFFENSE_A, "Thick Term Paper", 20, 24.0, 0.05);
            case 5:
                return new EquipableItem(5, Slot.OFFENSE_A, "Stick DELUXE", 30, 28.0, 0.07);
            case 6:
                return new EquipableItem(6, Slot.OFFENSE_B, "Hand-Me-Down Notes", 8, 10.0, 0.02);
            case 7:
                return new EquipableItem(7, Slot.OFFENSE_B, "Unopened Textbook", 70, 13.0, 0.02);
            case 8:
                return new EquipableItem(8, Slot.OFFENSE_B, "Tablet (Clay)", 20, 20.0, 0.00);
            case 9:
                return new EquipableItem(9, Slot.OFFENSE_B, "Tablet (Digital)", 30, 22.0, 0.05);
            case 10:
                return new EquipableItem(10, Slot.OFFENSE_B, "Laptop With Stickers", 50, 30.0, 0.08);
            case 11:
                return new EquipableItem(11, Slot.DEFENSE_A, "Jacket", 5, 0.0, 0.05);
            case 12:
                return new EquipableItem(12, Slot.DEFENSE_A, "School Uniform", 9, 0.05, 0.05);
            case 13:
                return new EquipableItem(13, Slot.DEFENSE_A, "Outdoor Jammies", 13, 0.07, 0.07);
            case 14:
                return new EquipableItem(14, Slot.DEFENSE_A, "Roommate's Clothes", 20, 0.10, 0.07);
            case 15:
                return new EquipableItem(15, Slot.DEFENSE_A, "TOGA!", 0, 0.13, 0.08);
            case 16:
                return new EquipableItem(16, Slot.DEFENSE_A, "Graduation Robe", 30, 0.18, 0.10);
            case 17:
                return new EquipableItem(17, Slot.DEFENSE_B, "Tinfoil", 1, 0.05, 0.00);
            case 18:
                return new EquipableItem(18, Slot.DEFENSE_B, "Hip Beanie", 5, 0.08, 0.02);
            case 19:
                return new EquipableItem(19, Slot.DEFENSE_B, "Beer Goggles", 10, 0.15, -0.05);
            case 20:
                return new EquipableItem(20, Slot.DEFENSE_B, "Ill-Advised Haircut", 20, 0.15, 0.02);
            case 21:
                return new EquipableItem(21, Slot.DEFENSE_B, "Sunglasses at Night", 22, 0.18, 0.05);
            case 22:
                return new EquipableItem(22, Slot.DEFENSE_B, "Mortarboard", 30, 0.20, 0.06);
            default:
                return null;
        }
    }

    @Override
    public Color getDisplayColor() {
        switch (getSlot()) {
            case OFFENSE_A: return Color.RED;
            case DEFENSE_A: return Color.BLUE;
            case OFFENSE_B: return Color.YELLOW;
            case DEFENSE_B: return Color.GREEN;
            default: throw new IllegalArgumentException("Invalid slot " + getSlot());
        }
    }

    @Override
    public String getDisplayText() {
        String offDefAB, evadeAcc;
        int formatStat;

        switch (getSlot()) {
            case OFFENSE_A:
                offDefAB = "Strength";
                evadeAcc = "Precision ";
                formatStat = (int)getBaseStat();
                break;
            case OFFENSE_B:
                offDefAB = "Knowledge";
                evadeAcc = "Precision ";
                formatStat = (int)getBaseStat();
                break;
            case DEFENSE_A:
                offDefAB = "Toughness";
                evadeAcc = "Evasion ";
                formatStat = (int)(getBaseStat() * 100);
                break;
            case DEFENSE_B:
                offDefAB = "Recall";
                evadeAcc = "Evasion ";
                formatStat = (int)(getBaseStat() * 100);
                break;
            default: throw new IllegalArgumentException("Invalid slot " + getSlot());
        }
        if (getToHitModifier() >= 0) evadeAcc = evadeAcc + "+";

        return getName() + "[" + offDefAB + " +" + formatStat + "](" + evadeAcc + (int)(getToHitModifier() * 100) + ")";
    }

}
