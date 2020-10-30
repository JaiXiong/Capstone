package engine;

import asset.character.AbstractCharacter;
import asset.world.Floor;
import io.gui.GUIManager;

import java.awt.*;
import java.util.ArrayList;

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
            for (AbstractCharacter character : characters) {
                handleAction(character, character.getNextAction());
            }
            GUIManager.getInstance().updateScreen(); //once all actions for this turn have been processed, redraw the screen
        } while (gameInProgress);
        //discard the current instance - if we begin a new game during this process, we'll create a new thread for it
        instance = null;
    }

    private void handleAction(AbstractCharacter actor, String action) {
        Point actorAt = actor.getLocation();
        switch (action) {
            case MOVE_NORTH:
                actor.setLocation(getDestinationOrTarget(actorAt, 0, -1));
                return;
            case MOVE_NORTH_EAST:
                actor.setLocation(getDestinationOrTarget(actorAt, 1, -1));
                return;
            case MOVE_EAST:
                actor.setLocation(getDestinationOrTarget(actorAt, 1, 0));
                return;
            case MOVE_SOUTH_EAST:
                actor.setLocation(getDestinationOrTarget(actorAt, 1, 1));
                return;
            case MOVE_SOUTH:
                actor.setLocation(getDestinationOrTarget(actorAt, 0, 1));
                return;
            case MOVE_SOUTH_WEST:
                actor.setLocation(getDestinationOrTarget(actorAt, -1, 1));
                return;
            case MOVE_WEST:
                actor.setLocation(getDestinationOrTarget(actorAt, -1, 0));
                return;
            case MOVE_NORTH_WEST:
                actor.setLocation(getDestinationOrTarget(actorAt, -1, -1));
                return;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
    }

    /**
     * Make sure an actor is able to perform the specified action. Should be used for all player input,
     * and possibly for AI.
     */
    public boolean validateAction(AbstractCharacter actor, String action) {
        Point actorAt = actor.getLocation();
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
                default:
                    return false;
        }
    }

    /**
     * Validate a movement action.
     */
    private boolean validateMovement(Point origin, int colChange, int rowChange) {
        Point destination = getDestinationOrTarget(origin, colChange, rowChange);
        if (!Gamestate.getInstance().getFloor().isTerrainPassableAt(destination.y, destination.x))
            return false; //impassable terrain at destination
        for (AbstractCharacter abstractCharacter : Gamestate.getInstance().getCharacters()) {
            if (abstractCharacter.getLocation().equals(destination))
                return false; //actor at destination
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
