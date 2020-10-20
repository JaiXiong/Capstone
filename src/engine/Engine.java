package engine;

import asset.character.AbstractCharacter;

import java.util.ArrayList;

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
                //todo - get the character's next action and execute.
                // Probably best to implement this as a field within Abstract character, and have it set by player
                // input for PlayerCharacter, and the AI when called for NonPlayerCharacters, but there may be other
                // ways to do this
                ; //NOP for now
            }
        } while (!gameInProgress);
        //discard the current instance - if we begin a new game during this process, we'll create a new thread for it
        instance = null;
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
