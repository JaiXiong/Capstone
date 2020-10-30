package asset.items;

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

    //the damage value modified by offense stats and used by offensive abilities
    //generally 0 for DEFENSE_A, DEFENSE_B, and UTIL slot items

    //the following values are how much the equipment adjust the player's stats by
    double baseStat, toHitModifier;

    public EquipableItem(int id, Slot slot, String name, int price, double baseStat,
        double toHitModifier) {

        super(id, name, price);
        SLOT = slot;
        this.baseStat = baseStat;
        this.toHitModifier = toHitModifier;
    }

    public Slot getSlot() {
        return SLOT;
    }

    public double getToHitModifier() {
        return toHitModifier;
    }

    public double getBaseStat() { return baseStat; }

    public static EquipableItem createEquipment(int itemID){
        switch (itemID) {
            case 0:
                return new EquipableItem(0, Slot.OFFENSE_A, "Stick", 0, 10.0, 0.0);
            case 1:
                return new EquipableItem(1, Slot.DEFENSE_A, "Jacket", 0, 0.0, 0.05);
            case 2:
                return new EquipableItem(2, Slot.DEFENSE_A, "School Uniform", 0, 0.0, 0.0);
            case 3:
                return new EquipableItem(3, Slot.DEFENSE_B, "Lab Coat", 0, 0.0, 0.0);
            case 4:
                return new EquipableItem(4, Slot.OFFENSE_A, "Sharpened Pencil", 0, 0.0, 0.0);
            case 5:
                return new EquipableItem(5, Slot.OFFENSE_A, "Fountain Pen", 0, 0.0, 0.0);
            case 6:
                return new EquipableItem(6, Slot.OFFENSE_B, "Tablet", 0, 0.0, 0.0);
            case 7:
                return new EquipableItem(7, Slot.OFFENSE_B, "Laptop", 0, 0.0, 0.0);
            case 8:
                return new EquipableItem(8, Slot.DEFENSE_B, "Graduation Robe", 0, 0.0, 0.0);
            default:
                return null;
        }
    }
}
