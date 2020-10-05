package main.items;
import main.items.EquipableItem.Slot;

/**
 * Lookup a pre-defined gear item by its name.
 */
public class GearTable {
    //TODO is storing gear info in a series of arrays ick?
    //TODO seems better than making a bunch of different constructors in EquipableItem?
    //equipment's name is indexed to its itemID
    private static final String[] nameTable =
            {"Stick",
             "Jacket"};

    //equipment's Slot is indexed to its itemID
    private static final EquipableItem.Slot[] slotTable =
            {Slot.OFFENSE_A,
             Slot.DEFENSE_A};

    private static final int[][] intTable =
            {{1, 0, 10},
             {1, 0, 0}};

    /* first index is equipment's itemID
     * second index is, in order:
     * [0]offenseA, [1]offenseB, [2]accuracy, [3]evade, [4]defenseA, [5]defenseB,
     * [6]defenseC, [7]defenseD
     */
    private static final double[][] doubleTable =
            {{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},
             {0.0,0.0,0.0,0.05,0.0,0.0,0.0,0.0}};

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
            return new EquipableItem(id, slotTable[id], nameTable[id], intTable[id][0],
                intTable[id][1], intTable[id][2], doubleTable[id][0], doubleTable[id][1],
                doubleTable[id][2], doubleTable[id][3], doubleTable[id][4], doubleTable[id][5],
                doubleTable[id][6], doubleTable[id][7]);
        }
        throw new CannotEquipException("Item ID error, ID number " + id);
    }

    public static class CannotEquipException extends java.lang.RuntimeException {
        public CannotEquipException(String errorMessage){
            super(errorMessage);
        }
    }
}