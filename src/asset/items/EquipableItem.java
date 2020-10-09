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
    int moveRate;
    int baseDamage;
    double offenseA, offenseB;
    double accuracy, evade;
    double defenseA, defenseB, defenseC, defenseD;

    public EquipableItem(int id, Slot slot, String name, int price, int moveRate, int baseDamage,
        double offenseA, double offenseB, double accuracy, double evade, double defenseA,
        double defenseB, double defenseC, double defenseD) {

        super(id, name, price);
        SLOT = slot;
        this.moveRate = moveRate;
        this.baseDamage = baseDamage;
        this.offenseA = offenseA;
        this.offenseB = offenseB;
        this.defenseA = defenseA;
        this.defenseB = defenseB;
        this.defenseC = defenseC;
        this.defenseD = defenseD;
        this.accuracy = accuracy;
        this.evade = evade;
    }

    public Slot getSlot() {
        return SLOT;
    }

    public int getMoveRate() {
        return moveRate;
    }

    public double getOffenseA() {
        return offenseA;
    }

    public double getOffenseB() {
        return offenseB;
    }

    public double getDefenseA() {
        return defenseA;
    }

    public double getDefenseB() {
        return defenseB;
    }

    public double getDefenseC() {
        return defenseC;
    }

    public double getDefenseD() {
        return defenseD;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getEvade() {
        return evade;
    }
}
