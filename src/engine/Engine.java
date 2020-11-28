package engine;

import asset.character.AbstractCharacter;
import asset.character.PlayerCharacter;
import asset.items.EquipableItem;
import asset.items.Item;
import asset.world.Floor;
import io.gui.GUIManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import static engine.ActionDefinitions.*;

public class Engine extends Thread {

    private static Engine instance = null;

    private boolean playerHasActionQueued = false;

    private boolean gameInProgress = true;

    private Engine(){}

    public static Engine getInstance() {
        if (instance == null)
            instance = new Engine();
        return instance;
    }

    @Override
    public void run() {
        ArrayList<AbstractCharacter> characters;
        do {
            while (!playerHasActionQueued && gameInProgress) {
                try {
                    sleep(125);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // if we broke the inner blocking loop because the game is no longer in progress, break the outer loop too
            if (!gameInProgress) break;
            //reset the player action flag to wait on further input
            playerHasActionQueued = false;
            //rather than saving a reference to this, we request it each iteration,
            // in case of major updates like a new floor being generated with a new list of characters
            characters = Gamestate.getInstance().getCharacters();
            AbstractCharacter character;
            for (Iterator<AbstractCharacter> i = characters.iterator(); i.hasNext();) {
                character = i.next();
                if (character.getHealth() <= 0) { //check that this character is alive - if not, remove it.
                    i.remove();
                    continue;
                }
                String action = character.getNextAction();
                if (!validateAction(character, action))
                    action = WAIT;
                handleAction(character, action);
            }
            GUIManager.getInstance().updateScreen(); //once all actions for this turn have been processed, redraw the screen
        } while (gameInProgress);
        //discard the current instance - if we begin a new game during this process, we'll create a new thread for it
        instance = null;
    }

    /**
     * Separate an action into a truncated action string and an index.
     * @param action the original action String
     * @return an array of Objects where the first item is the parsed action string
     * (or the original if not an indexed action), and the second is the parsed index(or -1 if not an indexed action)
     */
    private Object[] parseIndexedAction(String action) {
        Object[] indexParsedAction = new Object[2];
        int indexStart = action.length() - 2;
        String rawAction;
        Integer actionIndex;
        try {
            actionIndex = Integer.parseInt(action.substring(indexStart)); //store the parsed index
            rawAction = action.substring(0, indexStart); //truncate the action String
        } catch (NumberFormatException nfe) {
            actionIndex = -1; //no valid index
            rawAction = action; //do not truncate the action String
        }
        indexParsedAction[0] = rawAction;
        indexParsedAction[1] = actionIndex;
        return indexParsedAction;
    }

    private void handleAction(AbstractCharacter actor, String action) {
        Point actorAt = actor.getLocation();
        Point destination;
        Object[] indexedParse = parseIndexedAction(action);
        action = (String)indexedParse[0];
        int index = (Integer)indexedParse[1];
        switch (action) {
            case MOVE_NORTH:
                destination = getDestinationOrTarget(actorAt, 0, -1);
                break;
            case MOVE_NORTH_EAST:
                destination = getDestinationOrTarget(actorAt, 1, -1);
                break;
            case MOVE_EAST:
                destination = getDestinationOrTarget(actorAt, 1, 0);
                break;
            case MOVE_SOUTH_EAST:
                destination = getDestinationOrTarget(actorAt, 1, 1);
                break;
            case MOVE_SOUTH:
                destination = getDestinationOrTarget(actorAt, 0, 1);
                break;
            case MOVE_SOUTH_WEST:
                destination = getDestinationOrTarget(actorAt, -1, 1);
                break;
            case MOVE_WEST:
                destination = getDestinationOrTarget(actorAt, -1, 0);
                break;
            case MOVE_NORTH_WEST:
                destination = getDestinationOrTarget(actorAt, -1, -1);
                break;
            case WAIT:
                return; //do nothing
            case USE_AT:
                if (index < 0 || !(actor instanceof  PlayerCharacter))
                    throw new IllegalArgumentException("Tried to handle an invalid USE_ITEM action.");
                Item item = ((PlayerCharacter)actor).getInventory().get(index);
                Messages.addMessage((item instanceof EquipableItem)
                        ? Actions.equip(item)
                        : Actions.useItem(item));
                return;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
        AbstractCharacter target = Gamestate.getInstance().getCharacterAt(destination.y, destination.x);
        if (target != null) {
            actor.setTarget(target);
            Messages.addMessage(Actions.attack(actor, target));
        } else
            actor.setLocation(destination);
    }

    /**
     * Make sure an actor is able to perform the specified action. Should be used for all player input,
     * and possibly for AI.
     */
    public boolean validateAction(AbstractCharacter actor, String action) {
        Point actorAt = actor.getLocation();
        Object[] indexedParse = parseIndexedAction(action);
        action = (String)indexedParse[0];
        int index = (Integer)indexedParse[1];
        switch (action) {
            case MOVE_NORTH:
                return validateMovement(actorAt, 0, -1);
            case MOVE_NORTH_EAST:
                return validateMovement(actorAt, 1, -1);
            case MOVE_EAST:
                return validateMovement(actorAt, 1, 0);
            case MOVE_SOUTH_EAST:
                return validateMovement(actorAt, 1, 1);
            case MOVE_SOUTH:
                return validateMovement(actorAt, 0, 1);
            case MOVE_SOUTH_WEST:
                return validateMovement(actorAt, -1, 1);
            case MOVE_WEST:
                return validateMovement(actorAt, -1, 0);
            case MOVE_NORTH_WEST:
                return validateMovement(actorAt, -1, -1);
            case WAIT:
                return true; //this is always fine
            case USE_AT:
                if (index < 0) return false; //invalid index
                if (!(actor instanceof  PlayerCharacter)) return false; //npcs do not have inventories
                return (index < ((PlayerCharacter)actor).getInventory().size()); //return whether the player's inventory contains the passed index.
                default:
                    return false;
        }
    }

    /**
     * Validate a movement action.
     */
    private boolean validateMovement(Point origin, int colChange, int rowChange) {
        Floor thisFloor = Gamestate.getInstance().getFloor();
        Point destination = getDestinationOrTarget(origin, colChange, rowChange);
        if (!thisFloor.isTerrainPassableAt(destination.y, destination.x)) {

            //Players can attempt to open doors by moving into a closed door
            if (Gamestate.getInstance().getCharacterAt(origin.y,origin.x).getInitiativeID() == 0 &&
                    thisFloor.getTerrainType(destination.y, destination.x).equals("door")) {
                Messages.addMessage(Actions.openDoor(thisFloor.getTerrainAt(destination.y,destination.x)));
            }

            return false; //impassable terrain at destination
        }
        return true;
    }

    /**
     * Get a destination or target point from an origin point and a row and column change.
     */
    private Point getDestinationOrTarget(Point origin, int colChange, int rowChange) {
        return new Point(origin.x + colChange, origin.y + rowChange);
    }

    /**
     * Notify the engine that the player has queued an action, so it can stop blocking.
     */
    public void setPlayerHasActionQueued() {
        playerHasActionQueued = true;
    }

    /**
     * Motify the engine that the game has ended, so it can stop iterating for character actions and terminate.
     */
    public void endGame() {
        gameInProgress = false;
    }
}
