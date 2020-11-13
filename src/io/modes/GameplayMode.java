package io.modes;

import asset.character.PlayerCharacter;
import engine.Engine;
import engine.Gamestate;
import io.file.FileManager;
import io.gui.ConsoleInterface;
import io.gui.GUIManager;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static engine.ActionDefinitions.*;

/**
 * This is the mode for displaying the in-game environment, game messages, and any HUD information the player needs.
 * It is also responsible for handling all keyboard input which translates to valid game play actions or requests for
 * information which is available during a game session, such as player inventory.
 */
public class GameplayMode extends IOMode {
    @Override
    public void handle(KeyEvent ke) {
        int code = ke.getKeyCode();
        int mod = ke.getModifiersEx();
        String nextAction = null;
        switch (code) {
            case VK_S:
                if (mod == CTRL_DOWN_MASK) {
                    Engine.getInstance().endGame();
                    FileManager.saveGame();
                    Gamestate.clearInstance();
                    GUIManager.getInstance().revert(); //pop off the GamePlayMode
                    GUIManager.getInstance().revert(); //pop off the current MainMenuMode
                    GUIManager.getInstance().transitionTo(new MainMenuMode()); //create a new MainMenuMode to reflect new savegame state
                }
                break;
            case VK_Q:
                if (mod == CTRL_DOWN_MASK) {
                    Engine.getInstance().endGame();
                    Gamestate.clearInstance();
                    GUIManager.getInstance().revert();
                }
                break;
            case VK_UP: case VK_NUMPAD8:
                nextAction = MOVE_NORTH;
                break;
            case VK_NUMPAD9:
                nextAction = MOVE_NORTH_EAST;
                break;
            case VK_RIGHT: case VK_NUMPAD6:
                nextAction = MOVE_EAST;
                break;
            case VK_NUMPAD3:
                nextAction = MOVE_SOUTH_EAST;
                break;
            case VK_DOWN: case VK_NUMPAD2:
                nextAction = MOVE_SOUTH;
                break;
            case VK_NUMPAD1:
                nextAction = MOVE_SOUTH_WEST;
                break;
            case VK_LEFT: case VK_NUMPAD4:
                nextAction = MOVE_WEST;
                break;
            case VK_NUMPAD7:
                nextAction = MOVE_NORTH_WEST;
                break;
            //todo - lots here! movement commands, info commands, combat commands, etc.
            default:
                //nothing to do - unhandled input is ignored
        }
        // check if input set an action:
        if (nextAction != null) {
            PlayerCharacter playerCharacter = Gamestate.getInstance().getPlayerCharacter();
            // check if action is valid:
            if (Engine.getInstance().validateAction(playerCharacter, nextAction)) {
                // set the Player's next action to the input
                playerCharacter.setNextAction(nextAction);
                // let the engine know the player is ready to act
                Engine.getInstance().setPlayerHasActionQueued();
            } else {
                //todo - inform the player via message that his action could not be handled
            }
        }
    }

    @Override
    public void update(ConsoleInterface consoleInterface) {
        consoleInterface.clearScreen();
        consoleInterface.drawFloor();
        //todo: other relevant information, stats, enemy info, etc.
    }
}
