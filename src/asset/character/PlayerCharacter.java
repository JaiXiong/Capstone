package asset.character;

/* since the only persistent thing in game is the player character,
 * do we want to just put all the world state save game info in here?
 */
import asset.items.EquipableItem;
import asset.items.Item;
import console.ConsoleGlyph;
import engine.ActionDefinitions;
import engine.Engine;
import engine.Messages;
import io.file.FileManager;
import io.gui.GUIManager;
import io.modes.TextDisplayMode;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerCharacter extends AbstractCharacter implements Serializable {

    //maximum number of items the player's inventory may contain
    private static final int INVENTORY_CAPACITY = 16;

    int level;

    ArrayList<Item> inventory;
    ArrayList<Integer> keys;
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
        accuracy = 0.65;
        evade = 0.9;
        resistA = 0.95;
        resistB = 0.95;
        xp = 0;
        level = 1;
        inventory = new ArrayList<>();
        keys = new ArrayList<>();
        equip(EquipableItem.createEquipment(0)); //Stick
        equip(EquipableItem.createEquipment(11)); //Jacket
        actions = buildActions();
        keys.add(-1);
        inventory.add(Item.createItem(100));
        inventory.add(Item.createItem(103));
    }

    @Override
    public String[] buildActions() {
        return new String[]{
                ActionDefinitions.SPECIAL + "00",
                null,
                null,
                null
        };
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

    //applies Strength adjustments from gear, no cap
    @Override
    public double getOffenseA() {
        double totalOffA = offenseA;
        if (equipOffA != null) totalOffA = totalOffA * equipOffA.getBaseStat();

        return totalOffA;
    }

    //applies Knowledge adjustments from gear, no cap
    @Override
    public double getOffenseB() {
        double totalOffB = offenseB;
        if (equipOffB != null) totalOffB = totalOffB * equipOffB.getBaseStat();

        return totalOffB;
    }

    //applies Precision adjustments from gear, cap at 95%
    @Override
    public double getAccuracy() {
        double totalAcc = accuracy;
        if (equipOffA != null) totalAcc += equipOffA.getToHitModifier();
        if (equipOffB != null) totalAcc += equipOffB.getToHitModifier();

        if (totalAcc > .95) totalAcc = .95;

        return totalAcc;
    }

    //applies Evade adjustments from gear, cap at 40%
    @Override
    public double getEvade() {
        double totalEvade = evade;
        if (equipDefA != null) totalEvade -= equipDefA.getToHitModifier();
        if (equipDefB != null) totalEvade -= equipDefB.getToHitModifier();

        if (totalEvade < .4) totalEvade = .4;

        return totalEvade;
    }

    //applies Toughness adjustments from gear, cap at 10%
    @Override
    public double getResistA() {
        double totalResA = resistA;
        if (equipDefA != null) totalResA -= equipDefA.getBaseStat();

        if (totalResA < .1) totalResA = .1;

        return totalResA;
    }

    //applies Recall adjustments from gear, cap at 10%
    @Override
    public double getResistB() {
        double totalResB = resistB;
        if (equipDefB != null) totalResB -= equipDefB.getBaseStat();

        if (totalResB < .1) totalResB = .1;

        return totalResB;
    }

    @Override
    public void die() {
        FileManager.deleteSavedGame(); //permadeath
        Engine.getInstance().endGame(); //terminate the engine
        GUIManager.getInstance().revert(); //back out of the game screen
        GUIManager.getInstance().transitionTo(new TextDisplayMode("Game over! You have been defeated - better luck next time!"));
    }

    @Override
    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    //these setters are also just for testing
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

        Messages.addMessage("You have reached experience level " + level + ".");
        switch (level) {
            case 2:
                actions[1] = ActionDefinitions.SPECIAL + "01";
                Messages.addMessage("You gain the power of Rivalry!");
                Messages.addMessage("You and your rival take damage, stealing all its energy.");
                break;
            case 4:
                actions[2] = ActionDefinitions.SPECIAL + "02";
                Messages.addMessage("You gain the power of Top of the Class!");
                Messages.addMessage("You deal damage to everyone else on the floor.");
                break;
            case 8:
                actions[3] = ActionDefinitions.SPECIAL + "03";
                Messages.addMessage("You gain the power of Invigorating Focus!");
                Messages.addMessage("Drain your rival's energy, damaging them and healing yourself");
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

    /**
     * Finds key matching given unlock code, uses it if its found
     * @param unlockCode value for unlocking a door
     * @return true if key is found & used, false otherwise
     */
    public boolean keyRing(int unlockCode) {
        for (Integer key : keys) {
            if (key == unlockCode) {
                return keys.remove(key);
            }
        }
        for (Integer key : keys) {
            if (key == -1) {
                return keys.remove(key);
            }
        }
        return false;
    }

    /**
     *
     * @return how many keys the player has
     */
    public int getKeys() {
        return keys.size();
    }

    /**
     *
     * @param unlockCode value of the key to add
     * @return true if key added successfully, false otherwise
     */
    public boolean getKey(int unlockCode) {
        return keys.add(unlockCode);
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.WHITE, '@');
    }
}
