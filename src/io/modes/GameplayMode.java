package io.modes;

import asset.ColorDefinitions;
import asset.character.PlayerCharacter;
import asset.items.EquipableItem;
import engine.Engine;
import engine.Gamestate;
import engine.Messages;
import io.FormatUtility;
import io.file.FileManager;
import io.gui.ConsoleInterface;
import io.gui.GUIManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
        ArrayList messages = Messages.getMessages();
        final int firstLine = messages.size() > 2 ? 2 : messages.size() - 1;
        for (int line = firstLine; line >= 0 && messages.size() > (firstLine - line); --line) {
            consoleInterface.writeSingleLine(
                    line,
                    1,
                    "> " + messages.get(messages.size() - (1 + (firstLine - line))),
                    Color.BLACK,
                    line == firstLine ? Color.WHITE : Color.DARK_GRAY);
        }
        PlayerCharacter playerCharacter = Gamestate.getInstance().getPlayerCharacter();
        Dimension consoleSize = consoleInterface.getConsoleSize();
        int consoleHeight = consoleSize.height;
        int consoleWidth = consoleSize.width;
        int statLine = consoleHeight - 3;
        int gearLine1 = consoleHeight - 2;
        int gearLine2 = consoleHeight - 1;
        //stats:
        int playerHealthCurrent = playerCharacter.getHealth();
        int playerHealthMax = playerCharacter.getMaxHealth();
        double healthPercent = (double)playerHealthCurrent / (double)playerHealthMax;
        int writeColumn = consoleInterface.writeSingleLine(statLine, 0, "HP: ");
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn + 1,
                FormatUtility.percentage(healthPercent), Color.BLACK, FormatUtility.colorizeByPercentage(healthPercent));
        int playerEnergyCurrent = playerCharacter.getEnergy();
        int playerEnergyMax = playerCharacter.getMaxEnergy();
        double energyPercent = (double)playerEnergyCurrent / (double)playerEnergyMax;
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn + 1, "   Energy: ");
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn + 1,
                FormatUtility.percentage(energyPercent), Color.BLACK, FormatUtility.colorizeByPercentage(energyPercent));
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn + 1,
                "   Level: " + playerCharacter.getLevel() + "(" + playerCharacter.getXP() + " XP)");
        consoleInterface.writeSingleLine(statLine, writeColumn + 1,
                "                                                                    "); //fill line with blanks
        //gear:
        EquipableItem weapon1 = playerCharacter.getEquipOffA();
        writeColumn = consoleInterface.writeSingleLine(gearLine1, 0,
                "Weapon: " + (weapon1 == null ? "(none)" : weapon1.getName()));
        EquipableItem weapon2 = playerCharacter.getEquipOffB();
        writeColumn = consoleInterface.writeSingleLine(gearLine1, writeColumn + 1,
                "  Weapon: " + (weapon2 == null ? "(none)" : weapon2.getName()));
        consoleInterface.writeSingleLine(gearLine1, writeColumn + 1,
                "                                                                    "); //fill line with blanks
        EquipableItem armor1 = playerCharacter.getEquipDefA();
        writeColumn = consoleInterface.writeSingleLine(gearLine2, 0,
                "Armor: " + (armor1 == null ? "(none)" : armor1.getName()));
        EquipableItem armor2 = playerCharacter.getEquipDefB();
        writeColumn = consoleInterface.writeSingleLine(gearLine2, writeColumn + 1,
                "  Armor: " + (armor2 == null ? "(none)" : armor2.getName()));
        consoleInterface.writeSingleLine(gearLine2, writeColumn + 1,
                "                                                                    "); //fill line with blanks
        consoleInterface.updateScreen();
    }
}
