package main;

/* since the only persistent thing in game is the player character,
 * do we want to just put all the world state save game info in here?
 */
import main.items.EquipableItem;
import main.items.GearTable;
import main.items.Item;

import java.io.Serializable;

public class PlayerCharacter extends AbstractCharacter implements Serializable {

    /* characters may need the information on how they display
     * (which char, what color, what background) included in
     * the character class
     */

    int level;

    //TODO EXTERNAL classes for items need to be implemented
    Item inventory[];
    EquipableItem equipOffA;
    EquipableItem equipOffB;
    EquipableItem equipDefA;
    EquipableItem equipDefB;
    EquipableItem equipUtil;

    public PlayerCharacter() {
        initiativeID = 0;
        health = 100;
        maxHealth = 100;
        energy = 100;
        maxEnergy = 100;
        moveRate = 1;
        offenseA = 1.0;
        offenseB = 1.0;
        accuracy = 0.5;
        evade = 0.9;
        resistA = 0.95;
        resistB = 0.95;
        resistC = 0.95;
        resistD = 0.95;
        xp = 0;
        level = 1;
        inventory = new Item[16];
        equip(GearTable.lookupEquipmentByName("Stick"));
        equip(GearTable.lookupEquipmentByName("Jacket"));
        //no value for equipUtil in constructor, have to equip one later
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        //TODO fill in special actions
        return new String[1];
    }

    //this first block of getters right now is just for testing and probably
    //won't see any use in actual code.
    public int getXP(){ return xp; }

    public int getLevel(){ return level; }

    public String[] getActions(){ return actions; }

    public Item[] getInventory(){ return inventory; }

    public EquipableItem getEquipOffA(){ return equipOffA; }

    public EquipableItem getEquipOffB(){ return equipOffB; }

    public EquipableItem getEquipDefA(){ return equipDefA; }

    public EquipableItem getEquipDefB(){ return equipDefB; }

    public EquipableItem getEquipUtil(){ return equipUtil; }

    //TODO
    /* for now the getters will just give raw stats
     * but once persistant effects and gear are more filled out
     * we'll have to calculate those in too
     */
    @Override
    public int getMoveRate() {
        return moveRate;
    }

    @Override
    public double getOffenseA() {
        return offenseA;
    }

    @Override
    public double getOffenseB() {
        return offenseB;
    }

    @Override
    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public double getEvade() {
        return evade;
    }

    @Override
    public double getResistA() {
        return resistA;
    }

    @Override
    public double getResistB() {
        return resistB;
    }

    @Override
    public double getResistC() {
        return resistC;
    }

    @Override
    public double getResistD() {
        return resistD;
    }

    //these setters are also just for testing right now
    public void setMoveRate(int val){ moveRate = val; }

    public void setOffenseA(double val){ offenseA = val; }

    public void setOffenseB(double val){ offenseB = val; }

    public void setAccuracy(double val){ accuracy = val; }

    public void setEvade(double val){ evade = val; }

    public void setResistA(double val){ resistA = val; }

    public void setResistB(double val){ resistB = val; }

    public void setResistC(double val){ resistC = val; }

    public void setResistD(double val){ resistD = val; }

    /* @param amount how much XP is gained
     * adds to XP count, then checks for level up
     */
    public void gainXP(int amount){
        xp += amount;
        //TODO adjust level threshold
        if (xp > (level * 100)) {
            levelUp();
        }
    }

    public void levelUp(){
        level++;
        //TODO increase stats, possibly enable new ability
    }

    //TODO needs to be filled in
    public void equip(EquipableItem equipableItem){
        EquipableItem removedItem = null;
        switch (equipableItem.getSlot()) {
            case OFFENSE_A:
                removedItem = equipOffA;
                equipOffA = equipableItem;
                break;
            case OFFENSE_B:
                removedItem = equipOffB;
                equipOffB = equipableItem;
                break;
            case DEFENSE_A:
                removedItem = equipDefA;
                equipDefA = equipableItem;
                break;
            case DEFENSE_B:
                removedItem = equipDefB;
                equipDefB = equipableItem;
                break;
            case UTIL:
                removedItem = equipUtil;
                equipUtil = equipableItem;
                break;
                default:
                    throw new IllegalArgumentException("Equipped item slot not recognized: " + equipableItem.getSlot());
        }
        if (removedItem != null) {
            //todo - put it back in inventory
        }
    }

    //TODO add some other actions

    //TODO possibly include fields/methods for world state information
}
