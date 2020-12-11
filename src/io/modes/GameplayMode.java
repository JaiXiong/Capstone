package io.modes;

import asset.character.AbstractCharacter;
import asset.character.PlayerCharacter;
import asset.items.EquipableItem;
import asset.world.Terrain;
import engine.*;
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
        PlayerCharacter pc = Gamestate.getInstance().getPlayerCharacter();
        Point at;
        int code = ke.getKeyCode();
        boolean alt = ke.isAltDown();
        boolean ctrl = ke.isControlDown();
        boolean shift = ke.isShiftDown();
        String nextAction = null;
        switch (code) {
            case VK_S:
                if (ctrl) {
                    FileManager.saveGame();
                    Engine.getInstance().endGame();
                    Gamestate.clearInstance();
                    GUIManager.getInstance().revert(); //pop off the GamePlayMode
                    GUIManager.getInstance().revert(); //pop off the current MainMenuMode
                    GUIManager.getInstance().transitionTo(new MainMenuMode()); //create a new MainMenuMode to reflect new savegame state
                }
                break;
            case VK_Q:
                if (ctrl) {
                    Engine.getInstance().endGame();
                    Gamestate.clearInstance();
                    GUIManager.getInstance().revert();
                }
                break;
            case VK_UP: case VK_NUMPAD8:
                nextAction = alt ? ALT_NORTH : ctrl ? CTRL_NORTH : MOVE_NORTH;
                break;
            case VK_NUMPAD9:
                nextAction = alt ? ALT_NORTH_EAST : ctrl ? CTRL_NORTH_EAST : MOVE_NORTH_EAST;
                break;
            case VK_RIGHT: case VK_NUMPAD6:
                nextAction = alt ? ALT_EAST : ctrl ? CTRL_EAST : MOVE_EAST;
                break;
            case VK_NUMPAD3:
                nextAction = alt ? ALT_SOUTH_EAST : ctrl ? CTRL_SOUTH_EAST : MOVE_SOUTH_EAST;
                break;
            case VK_DOWN: case VK_NUMPAD2:
                nextAction = alt ? ALT_SOUTH : ctrl ? CTRL_SOUTH : MOVE_SOUTH;
                break;
            case VK_NUMPAD1:
                nextAction = alt ? ALT_SOUTH_WEST : ctrl ? CTRL_SOUTH_WEST : MOVE_SOUTH_WEST;
                break;
            case VK_LEFT: case VK_NUMPAD4:
                nextAction = alt ? ALT_WEST : ctrl ? CTRL_WEST : MOVE_WEST;
                break;
            case VK_NUMPAD7:
                nextAction = alt ? ALT_NORTH_WEST : ctrl ? CTRL_NORTH_WEST : MOVE_NORTH_WEST;
                break;
            case VK_SLASH:
                if (shift) {
                    GUIManager.getInstance().transitionTo(new HelpScreenMode());
                }
                break;
            case VK_U: //use an item from inventory
                GUIManager.getInstance().transitionTo(new UseItemSelectMenuMode());
                return; //don't break - next action info and engine notification will be handled by the selection menu
            case VK_D: //discard an item from inventory
                GUIManager.getInstance().transitionTo(new DiscardItemSelectMenuMode());
                return; //don't break - next action info and engine notification will be handled by the selection menu
            case VK_PERIOD: // ">"
                if (shift) {
                    at = pc.getLocation();
                    if (Gamestate.getInstance().getFloor().getTerrainType(at.y, at.x).equals(Terrain.TileType.STAIRCASE.getName())) {
                        Messages.addMessage("You advance to the next floor.");
                        Gamestate.getInstance().nextFloor();
                        GUIManager.getInstance().updateScreen();
                        return;
                    } else {
                        Messages.addMessage("There is no staircase here.");
                    }
                }
                break;
            case VK_COMMA: // "<"
                if (shift) {
                    at = pc.getLocation();
                    if (Gamestate.getInstance().getFloor().getTerrainType(at.y, at.x).equals(Terrain.TileType.EMERGENCY_EXIT.getName())) {
                        Messages.addMessage("You escape through the emergency exit.");
                        Gamestate.getInstance().returnToBase();
                        GUIManager.getInstance().updateScreen();
                        return;
                    } else {
                        Messages.addMessage("There is no emergency exit here.");
                    }
                }
                break;
            case VK_F1: // "F1"
                if (Actions.playerCanUseSpecial(0)) {
                    nextAction = SPECIAL + "00";
                } else {
                    Messages.addMessage("You don't have the energy to Cram right now.");
                }
                break;
            case VK_F2: // "F2"
                if (pc.getActions()[1] == null) break;
                if (Actions.playerCanUseSpecial(1)) {
                    nextAction = SPECIAL + "01";
                } else {
                    Messages.addMessage("Your health is too low to use Rivalry.");
                }
                break;
            case VK_F3: // "F3"
                if (pc.getActions()[2] == null) break;
                if (Actions.playerCanUseSpecial(2)) {
                    nextAction = SPECIAL + "02";
                } else {
                    Messages.addMessage("You don't have the energy to rise to the Top of the Class.");
                }
                break;
            case VK_F4: // "F4"
                if (pc.getActions()[3] == null) break;
                if (Actions.playerCanUseSpecial(3)) {
                    nextAction = SPECIAL + "03";
                } else {
                    Messages.addMessage("You don't have the energy to Focus right now.");
                }
                break;
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
                Messages.addMessage("Action could not be performed.");
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
                    "> " + messages.get(messages.size() - (1 + (firstLine - line)))
                            + "                                                                                  ",
                    Color.BLACK,
                    line == firstLine ? Color.CYAN : Color.BLUE);
        }
        PlayerCharacter playerCharacter = Gamestate.getInstance().getPlayerCharacter();
        Dimension consoleSize = consoleInterface.getConsoleSize();
        int consoleHeight = consoleSize.height;
        int consoleWidth = consoleSize.width;
        int specialLine = consoleHeight - 4;
        int statLine = consoleHeight - 3;
        int gearLine1 = consoleHeight - 2;
        int gearLine2 = consoleHeight - 1;
        int writeColumn = consoleInterface.writeSingleLine(specialLine, 0, "Special Abilities: ", Color.LIGHT_GRAY, Color.BLACK);
        //special abilities:
        for (int i = 0; i < playerCharacter.getActions().length; ++i) {
            String ability = playerCharacter.getActions()[i];
            writeColumn =
                    consoleInterface.writeSingleLine(
                            specialLine,
                            writeColumn + 1,
                            "[F" + (i + 1) + "]",
                            Color.LIGHT_GRAY,
                            ability == null
                                    ? Color.LIGHT_GRAY //invisible
                                    : Actions.playerCanUseSpecial(i)
                                    ? Color.CYAN
                                    : Color.DARK_GRAY
                    );
        }
        consoleInterface.writeSingleLine(specialLine, writeColumn + 1,
                "                                                           ", Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        writeColumn = 0;
        //stats:
        int playerHealthCurrent = playerCharacter.getHealth();
        int playerHealthMax = playerCharacter.getMaxHealth();
        double healthPercent = (double)playerHealthCurrent / (double)playerHealthMax;
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn, "HP: ", Color.LIGHT_GRAY, Color.BLACK);
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn + 1,
                FormatUtility.percentage(healthPercent), Color.LIGHT_GRAY, FormatUtility.colorizeByPercentage(healthPercent));
        int playerEnergyCurrent = playerCharacter.getEnergy();
        int playerEnergyMax = playerCharacter.getMaxEnergy();
        double energyPercent = (double)playerEnergyCurrent / (double)playerEnergyMax;
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn + 1, "   Energy: ", Color.LIGHT_GRAY, Color.BLACK);
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn + 1,
                FormatUtility.percentage(energyPercent), Color.LIGHT_GRAY, FormatUtility.colorizeByPercentage(energyPercent));
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn + 1,
                "   Level: " + playerCharacter.getLevel() + "(" + playerCharacter.getXP() + " XP)", Color.LIGHT_GRAY, Color.BLACK);
        writeColumn = consoleInterface.writeSingleLine(statLine, writeColumn + 1, "   Keys: " + playerCharacter.getKeys(), Color.LIGHT_GRAY, Color.BLACK);
        consoleInterface.writeSingleLine(statLine, writeColumn + 1,
                "                                                                    ", Color.LIGHT_GRAY, Color.BLACK); //fill line with blanks
        //gear:
        EquipableItem weapon1 = playerCharacter.getEquipOffA();
        writeColumn = consoleInterface.writeSingleLine(gearLine1, 0,
                "Weapon: " + (weapon1 == null ? "(none)" : weapon1.getName()), Color.LIGHT_GRAY, Color.RED);
        EquipableItem weapon2 = playerCharacter.getEquipOffB();
        writeColumn = consoleInterface.writeSingleLine(gearLine1, writeColumn + 1,
                "  Tool: " + (weapon2 == null ? "(none)" : weapon2.getName()), Color.LIGHT_GRAY, Color.YELLOW);
        consoleInterface.writeSingleLine(gearLine1, writeColumn + 1,
                "                                                                    ", Color.LIGHT_GRAY, Color.BLACK); //fill line with blanks
        EquipableItem armor1 = playerCharacter.getEquipDefA();
        writeColumn = consoleInterface.writeSingleLine(gearLine2, 0,
                "Clothes: " + (armor1 == null ? "(none)" : armor1.getName()), Color.LIGHT_GRAY, Color.BLUE);
        EquipableItem armor2 = playerCharacter.getEquipDefB();
        writeColumn = consoleInterface.writeSingleLine(gearLine2, writeColumn + 1,
                "  Hat: " + (armor2 == null ? "(none)" : armor2.getName()), Color.LIGHT_GRAY, Color.GREEN);
        consoleInterface.writeSingleLine(gearLine2, writeColumn + 1,
                "                                                                    ", Color.LIGHT_GRAY, Color.BLACK); //fill line with blanks
        //stats:
        writeColumn = consoleWidth - 13;
        int writeRow = 3;
        double playerAccuracy = playerCharacter.getAccuracy();
        double playerOffenseA = playerCharacter.getOffenseA();
        double playerOffenseB = playerCharacter.getOffenseB();
        double playerEvade = playerCharacter.getEvade();
        double playerResistA = playerCharacter.getResistA();
        double playerResistB = playerCharacter.getResistB();
        consoleInterface.writeSingleLine(writeRow++, writeColumn, "Stats:                   ", Color.DARK_GRAY);
        consoleInterface.writeSingleLine(writeRow++, writeColumn, "Precision:" + FormatUtility.percentage(playerAccuracy) + "             ", Color.DARK_GRAY, Color.YELLOW);
        consoleInterface.writeSingleLine(writeRow++, writeColumn, "Strength: " + (int)playerOffenseA + "             ", Color.DARK_GRAY, Color.YELLOW);
        consoleInterface.writeSingleLine(writeRow++, writeColumn, "Knowledge:" + (int)playerOffenseB + "             ", Color.DARK_GRAY, Color.YELLOW);
        consoleInterface.writeSingleLine(writeRow++, writeColumn, "Evasion:  " + FormatUtility.percentage(1.0 - playerEvade) + "             ", Color.DARK_GRAY, Color.YELLOW);
        consoleInterface.writeSingleLine(writeRow++, writeColumn, "Toughness:" + FormatUtility.percentage(1.0 - playerResistA) + "             ", Color.DARK_GRAY, Color.YELLOW);
        consoleInterface.writeSingleLine(writeRow++, writeColumn, "Recall:   " + FormatUtility.percentage(1.0 - playerResistB) + "             ", Color.DARK_GRAY, Color.YELLOW);
        //target:
        writeRow++;
        consoleInterface.writeSingleLine(writeRow++, writeColumn,"Rival:         ", Color.RED, Color.LIGHT_GRAY);
        AbstractCharacter playerTarget = playerCharacter.getTarget();
        if (playerTarget == null) {
            consoleInterface.writeSingleLine(writeRow, writeColumn, "<no rival>     ", Color.RED, Color.LIGHT_GRAY);
        } else {
            consoleInterface.writeSingleLine(writeRow++, writeColumn, playerTarget.getLeadName() + "              ",
                    Color.RED, Color.LIGHT_GRAY);
            double targetHPPct = (double)playerTarget.getHealth() / (double)playerTarget.getMaxHealth();
            consoleInterface.writeSingleLine(writeRow, writeColumn, "HP: " + FormatUtility.percentage(targetHPPct)
                    + "            ", Color.RED, Color.LIGHT_GRAY);
        }
        consoleInterface.updateScreen();
    }
}
