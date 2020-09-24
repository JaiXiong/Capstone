package main.items;

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

    public EquipableItem(Slot slot, String name) {
        super(name);
        SLOT = slot;
    }

    public Slot getSlot() {
        return SLOT;
    }
}
