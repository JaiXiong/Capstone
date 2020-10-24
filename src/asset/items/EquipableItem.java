package asset.items;

/**
 * Define items which can be equipped as gear.
 */
public class EquipableItem extends Item {
    public enum Slot {
        OFFENSE_A,
        OFFENSE_B,
        DEFENSE_A,
        DEFENSE_B,
        UTIL
    }
    private final Slot SLOT;

    //the damage value modified by offense stats and used by offensive abilities
    //generally 0 for DEFENSE_A, DEFENSE_B, and UTIL slot items

    //the following values are how much the equipment adjust the player's stats by
    double baseStat, accuracy, evade;

    public EquipableItem(int id, Slot slot, String name, int price, double baseStat,
        double accuracy, double evade) {

        super(id, name, price);
        SLOT = slot;
        this.baseStat = baseStat;
        this.accuracy = accuracy;
        this.evade = evade;
    }

    public Slot getSlot() {
        return SLOT;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getEvade() {
        return evade;
    }

    public double getBaseStat() { return baseStat; }
}
