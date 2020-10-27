package asset.character;

/* since the only persistent thing in game is the player character,
 * do we want to just put all the world state save game info in here?
 */
import asset.items.EquipableItem;
import asset.items.GearTable;
import asset.items.Item;
import console.ConsoleGlyph;

import java.awt.*;
import java.io.Serializable;

public class PlayerCharacter extends AbstractCharacter implements Serializable {

    /* characters may need the information on how they display
     * (which char, what color, what background) included in
     * the character class
     */

    int level;

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
        //todo - this constructor is in an infinite loop. I suspect the gear lookup. -ncb
//        equip(GearTable.lookupEquipmentByName("Stick"));
//        equip(GearTable.lookupEquipmentByName("Jacket"));
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

    /* TODO possibly add persistant effects to mutable stat getters
     * also figure out if moveRate is something that changes,
     * or if we'll even use it
     */
    @Override
    public int getMoveRate() {
        return moveRate;
    }

    //applies offenseA adjustments from gear, no cap
    @Override
    public double getOffenseA() {
        double totalOffA = offenseA;
        if (equipOffA != null) totalOffA += equipOffA.getBaseStat();
        if (equipOffB != null) totalOffA += equipOffB.getBaseStat();
        if (equipDefA != null) totalOffA += equipDefA.getBaseStat();
        if (equipDefB != null) totalOffA += equipDefB.getBaseStat();
        if (equipUtil != null) totalOffA += equipUtil.getBaseStat();

        return totalOffA;
    }

    //applies offenseB adjustments from gear, no cap
    @Override
    public double getOffenseB() {
        double totalOffB = offenseB;
        if (equipOffA != null) totalOffB += equipOffA.getBaseStat();
        if (equipOffB != null) totalOffB += equipOffB.getBaseStat();
        if (equipDefA != null) totalOffB += equipDefA.getBaseStat();
        if (equipDefB != null) totalOffB += equipDefB.getBaseStat();
        if (equipUtil != null) totalOffB += equipUtil.getBaseStat();

        return totalOffB;
    }

    //applies accuracy adjustments from gear, cap at 95%
    @Override
    public double getAccuracy() {
        double totalAcc = accuracy;
        if (equipOffA != null) totalAcc += equipOffA.getAccuracy();
        if (equipOffB != null) totalAcc += equipOffB.getAccuracy();
        if (equipDefA != null) totalAcc += equipDefA.getAccuracy();
        if (equipDefB != null) totalAcc += equipDefB.getAccuracy();
        if (equipUtil != null) totalAcc += equipUtil.getAccuracy();

        if (totalAcc > .95) totalAcc = .95;

        return totalAcc;
    }

    //applies accuracy adjustments from gear, cap at 40%
    @Override
    public double getEvade() {
        double totalEvade = accuracy;
        if (equipOffA != null) totalEvade += equipOffA.getEvade();
        if (equipOffB != null) totalEvade += equipOffB.getEvade();
        if (equipDefA != null) totalEvade += equipDefA.getEvade();
        if (equipDefB != null) totalEvade += equipDefB.getEvade();
        if (equipUtil != null) totalEvade += equipUtil.getEvade();

        if (totalEvade < .4) totalEvade = .4;

        return totalEvade;
    }

    //applies accuracy adjustments from gear, cap at 10%
    @Override
    public double getResistA() {
        double totalResA = resistA;
        if (equipOffA != null) totalResA += equipOffA.getBaseStat();
        if (equipOffB != null) totalResA += equipOffB.getBaseStat();
        if (equipDefA != null) totalResA += equipDefA.getBaseStat();
        if (equipDefB != null) totalResA += equipDefB.getBaseStat();
        if (equipUtil != null) totalResA += equipUtil.getBaseStat();

        if (totalResA < .1) totalResA = .1;

        return totalResA;
    }

    //applies accuracy adjustments from gear, cap at 10%
    @Override
    public double getResistB() {
        double totalResB = resistB;
        if (equipOffA != null) totalResB += equipOffA.getBaseStat();
        if (equipOffB != null) totalResB += equipOffB.getBaseStat();
        if (equipDefA != null) totalResB += equipDefA.getBaseStat();
        if (equipDefB != null) totalResB += equipDefB.getBaseStat();
        if (equipUtil != null) totalResB += equipUtil.getBaseStat();

        if (totalResB < .1) totalResB = .1;

        return totalResB;
    }

    //applies accuracy adjustments from gear, cap at 10%
    @Override
    public double getResistC() {
        double totalResC = resistC;
        if (equipOffA != null) totalResC += equipOffA.getBaseStat();
        if (equipOffB != null) totalResC += equipOffB.getBaseStat();
        if (equipDefA != null) totalResC += equipDefA.getBaseStat();
        if (equipDefB != null) totalResC += equipDefB.getBaseStat();
        if (equipUtil != null) totalResC += equipUtil.getBaseStat();

        if (totalResC < .1) totalResC = .1;

        return totalResC;
    }

    //applies accuracy adjustments from gear, cap at 10%
    @Override
    public double getResistD() {
        double totalResD = resistD;
        if (equipOffA != null) totalResD += equipOffA.getBaseStat();
        if (equipOffB != null) totalResD += equipOffB.getBaseStat();
        if (equipDefA != null) totalResD += equipDefA.getBaseStat();
        if (equipDefB != null) totalResD += equipDefB.getBaseStat();
        if (equipUtil != null) totalResD += equipUtil.getBaseStat();

        if (totalResD < .1) totalResD = .1;

        return totalResD;
    }

    //these setters are also just for testing right now
    public void setBaseStat(int val){ moveRate = val; }

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

        while ((xp >= (level * 100)) && (level < 20)) {
            levelUp();
        }
    }

    public void levelUp(){
        xp = xp - (level * 100);
        level++;

        //TODO possibly adjust stat increases
        health = (int) (health * 1.1);
        maxHealth = (int) (maxHealth * 1.1);
        energy = (int) (energy * 1.05);
        maxEnergy = (int) (maxEnergy * 1.05);
        offenseA = offenseA * 1.05;
        offenseB = offenseB * 1.05;
        accuracy = accuracy * 1.03;
        evade = evade * .98;
        resistA = resistA * .98;
        resistB = resistB * .98;
        resistC = resistC * .98;
        resistD = resistD * .98;

        switch (level) {
            //TODO number of cases is arbitrary, we can add/remove
            case 2:
                //TODO add new ability
                break;
            case 3:
                //TODO add new ability
                break;
            case 4:
                //TODO add new ability
                break;
            case 6:
                //TODO add new ability
                break;
            case 8:
                //TODO add new ability
                break;
            case 10:
                //TODO add new ability
                break;
            case 13:
                //TODO add new ability
                break;
            case 16:
                //TODO add new ability
                break;
            case 20:
                //TODO add new ability
                break;
        }
    }

    /* @param item to equip (object, not name or ID)
     * equips given item, removes it from inventory,
     * and places the previously equipped item
     * (if any) into inventory
     */
    public void equip(EquipableItem equipableItem){
        Item removedItem = null;
        switch (equipableItem.getSlot()) {
            case OFFENSE_A:
                removedItem = equipOffA;
                equipOffA = equipableItem;
                removeFromInventory(equipableItem.getItemID());
                break;
            case OFFENSE_B:
                removedItem = equipOffB;
                equipOffB = equipableItem;
                removeFromInventory(equipableItem.getItemID());
                break;
            case DEFENSE_A:
                removedItem = equipDefA;
                equipDefA = equipableItem;
                removeFromInventory(equipableItem.getItemID());
                break;
            case DEFENSE_B:
                removedItem = equipDefB;
                equipDefB = equipableItem;
                removeFromInventory(equipableItem.getItemID());
                break;
            case UTIL:
                removedItem = equipUtil;
                equipUtil = equipableItem;
                removeFromInventory(equipableItem.getItemID());
                break;
                default:
                    throw new IllegalArgumentException("Equipped item slot not recognized: " + equipableItem.getSlot());
        }
        if (removedItem != null) {
            addToInventory(removedItem);
        }
    }

    /* @param item to put into inventory
     * returns true if item is put into inventory
     * false otherwise (inventory is full)
     */
    public boolean addToInventory(Item item){
        for (int i=0;i<16;i++){
            if (inventory[i] == null) {
                inventory[i] = item;
                return true;
            }
        }
        return false;
    }

    /* @param itemID of item to remove from inventory
     * returns the removed item if successful, null otherwise
     * keeps all non-null slots together at low indices
     */
    public Item removeFromInventory(int itemId){
        Item removedItem = null;
        int backIndex = 16;
        int frontIndex = 0;

        while (frontIndex < backIndex){

            if (inventory[frontIndex] != null && inventory[frontIndex].getItemID() == itemId){
                removedItem = inventory[frontIndex];
                inventory[frontIndex] = null;
            }

            if (inventory[frontIndex] == null) {
                while ((backIndex - 1) > frontIndex) {
                    backIndex--;
                    if (inventory[backIndex] != null) {
                        inventory[frontIndex] = inventory[backIndex];
                        break;
                    }
                }
            }
            else {
                frontIndex++;
            }
        }
        //a return was missing here???
        return null;
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.WHITE, '@');
    }

    //TODO add some other actions

    //TODO possibly include fields/methods for world state information
}
