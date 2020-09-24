package main.character;

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
        //TODO adjust starting player info
        initiativeID = 0;
        health = 100;
        energy = 100;
        moveRate = 1;
        offenseA = 1.0;
        offenseB = 1.0;
        accuracy = 1.0;
        evade = 1.0;
        resistA = 1.0;
        resistB = 1.0;
        resistC = 1.0;
        resistD = 1.0;
        xp = 0;
        level = 1;
        inventory = new Item[16];
        //TODO some way to look up gear needs to be implemented
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
