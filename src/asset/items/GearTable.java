package asset.items;
import asset.items.EquipableItem.Slot;

/**
 * Lookup a pre-defined gear item by its name.
 */
public class GearTable {
    //TODO is storing gear info in a series of arrays ick?
    //TODO seems better than making a bunch of different constructors in EquipableItem?
    //TODO could make constructor with name, slotid, slotvalue, etc instead of multiple constructors
    //equipment's name is indexed to its itemID
    private static final String[] nameTable =
            {"Stick",
             "Jacket",
             "School Uniform",
             "Lab Coat",
             "Sharp Pencil",
             "Fountain Pen",
             "Tablet",
             "Laptop",
             "Graduation Robes"};

    //equipment's Slot is indexed to its itemID
    private static final EquipableItem.Slot[] slotTable =
            {Slot.OFFENSE_A,
             Slot.OFFENSE_B,
             Slot.DEFENSE_A,
             Slot.DEFENSE_B,
             Slot.UTIL};

    /* first index is equipment's itemID
     * second index is, in order:
     * [0]baseStat, [1]accuracy, [2]evade
     */
    private static final double[][] doubleTable =
            {{0.0,0.0,0.0},
             {0.0,0.0,0.05},
             {0.0,0.0,0.0},
             {0.0,0.0,0.0},
             {0.0,0.0,0.0},
             {0.0,0.0,0.0},
             {0.0,0.0,0.0},
             {0.0,0.0,0.0},
             {0.0,0.0,0.0}};

    /*price array*/
    private static final int [] priceTable =
            {0, 0, 0, 0, 0, 0, 0, 0, 0};

    /* @param name: item name
     * equipment lookup used by more user-facing
     * since the user could attempt to equip an invalid name, throws an error if
     * the name isn't found
     */
    public static EquipableItem lookupEquipmentByName(String name)
        throws CannotEquipException {
        for (int i=0;i<nameTable.length;i++) {
            if (nameTable[i].equalsIgnoreCase(name)) { return lookupEquipmentByID(i); }
        }
        throw new CannotEquipException("Can't equip " + name);
    }

    /* @param id: item ID
     * equipment lookup used by stuff further from user
     * throws an error if item ID isn't a valid equipment
     */
    public static EquipableItem lookupEquipmentByID(int id)
        throws CannotEquipException {
        if (nameTable.length > id){
            return new EquipableItem(id, slotTable[id], nameTable[id], priceTable[id], doubleTable[id][0], doubleTable[id][1],
                doubleTable[id][2]);
        }
        throw new CannotEquipException("Item ID error, ID number " + id);
    }

    public static class CannotEquipException extends java.lang.RuntimeException {
        public CannotEquipException(String errorMessage){
            super(errorMessage);
        }
    }
}