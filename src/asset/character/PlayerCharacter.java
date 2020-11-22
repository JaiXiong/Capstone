package asset.character;

/* since the only persistent thing in game is the player character,
 * do we want to just put all the world state save game info in here?
 */
import asset.items.EquipableItem;
import asset.items.Item;
import console.ConsoleGlyph;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerCharacter extends AbstractCharacter implements Serializable {

    //maximum number of items the player's inventory may contain
    private static final int INVENTORY_CAPACITY = 16;

    /* characters may need the information on how they display
     * (which char, what color, what background) included in
     * the character class
     */

    int level;

    ArrayList<Item> inventory;
    EquipableItem equipOffA;
    EquipableItem equipOffB;
    EquipableItem equipDefA;
    EquipableItem equipDefB;

    public PlayerCharacter() {
        initiativeID = 0;
        name = "you";
        leadName = "You";
        health = 100;
        maxHealth = 100;
        energy = 100;
        maxEnergy = 100;
        offenseA = 1.0;
        offenseB = 1.0;
        accuracy = 0.5;
        evade = 0.9;
        resistA = 0.95;
        resistB = 0.95;
        xp = 0;
        level = 1;
        inventory = new ArrayList<>();
        equip(EquipableItem.createEquipment(0)); //Stick
        equip(EquipableItem.createEquipment(1)); //Jacket
        actions = buildActions();

        //TODO PLAYTEST remove this key before release version
        inventory.add(Item.createItem(-1));
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

    public ArrayList<Item> getInventory(){ return inventory; }

    public EquipableItem getEquipOffA(){ return equipOffA; }

    public EquipableItem getEquipOffB(){ return equipOffB; }

    public EquipableItem getEquipDefA(){ return equipDefA; }

    public EquipableItem getEquipDefB(){ return equipDefB; }

    private String nextAction = null;

    /* TODO possibly add persistant effects to mutable stat getters
     * also figure out if moveRate is something that changes,
     * or if we'll even use it
     */

    //applies offenseA adjustments from gear, no cap
    @Override
    public double getOffenseA() {
        double totalOffA = offenseA;
        if (equipOffA != null) totalOffA = totalOffA * equipOffA.getBaseStat();

        return totalOffA;
    }

    //applies offenseB adjustments from gear, no cap
    @Override
    public double getOffenseB() {
        double totalOffB = offenseB;
        if (equipOffB != null) totalOffB = totalOffB * equipOffB.getBaseStat();

        return totalOffB;
    }

    //applies accuracy adjustments from gear, cap at 95%
    @Override
    public double getAccuracy() {
        double totalAcc = accuracy;
        if (equipOffA != null) totalAcc += equipOffA.getToHitModifier();
        if (equipOffB != null) totalAcc += equipOffB.getToHitModifier();

        if (totalAcc > .95) totalAcc = .95;

        return totalAcc;
    }

    //applies accuracy adjustments from gear, cap at 40%
    @Override
    public double getEvade() {
        double totalEvade = accuracy;
        if (equipDefA != null) totalEvade -= equipDefA.getToHitModifier();
        if (equipDefB != null) totalEvade -= equipDefB.getToHitModifier();

        if (totalEvade < .4) totalEvade = .4;

        return totalEvade;
    }

    //applies accuracy adjustments from gear, cap at 10%
    @Override
    public double getResistA() {
        double totalResA = resistA;
        if (equipDefA != null) totalResA -= equipDefA.getBaseStat();

        if (totalResA < .1) totalResA = .1;

        return totalResA;
    }

    //applies accuracy adjustments from gear, cap at 10%
    @Override
    public double getResistB() {
        double totalResB = resistB;
        if (equipDefB != null) totalResB -= equipDefB.getBaseStat();

        if (totalResB < .1) totalResB = .1;

        return totalResB;
    }

    @Override
    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    //these setters are also just for testing right now
    public void setOffenseA(double val){ offenseA = val; }

    public void setOffenseB(double val){ offenseB = val; }

    public void setAccuracy(double val){ accuracy = val; }

    public void setEvade(double val){ evade = val; }

    public void setResistA(double val){ resistA = val; }

    public void setResistB(double val){ resistB = val; }

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
        Item removedItem;
        switch (equipableItem.getSlot()) {
            case OFFENSE_A:
                removedItem = equipOffA;
                equipOffA = equipableItem;
                removeFromInventory(equipableItem);
                break;
            case OFFENSE_B:
                removedItem = equipOffB;
                equipOffB = equipableItem;
                removeFromInventory(equipableItem);
                break;
            case DEFENSE_A:
                removedItem = equipDefA;
                equipDefA = equipableItem;
                removeFromInventory(equipableItem);
                break;
            case DEFENSE_B:
                removedItem = equipDefB;
                equipDefB = equipableItem;
                removeFromInventory(equipableItem);
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
        if (inventory.size() < INVENTORY_CAPACITY) {
            inventory.add(item);
            return true;
        }
        return false;
    }

    /**
     * @param item Item object to remove from inventory
     * @return true if item was in inventory, false if not
     */
    public boolean removeFromInventory(Item item){
        return inventory.remove(item);
    }

    public boolean keyRing(int unlockCode) {
        for (Item item : inventory) {
            if (item != null && item.getItemID() == unlockCode) {
                return removeFromInventory(item);
            }
        }
        for (Item item : inventory) {
            if (item != null && item.getItemID() == -1) {
                return removeFromInventory(item);
            }
        }
        return false;
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.WHITE, '@');
    }

    //TODO add some other actions

    //TODO possibly include fields/methods for world state information
}
