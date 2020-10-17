package io.modes;

import engine.Gamestate;
import io.gui.ConsoleInterface;
import io.gui.GUIManager;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

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
        switch (code) {
            case VK_ESCAPE:
                GUIManager.getInstance().revert();
                break;
            //todo - lots here! movement commands, info commands, combat commands, etc.
            default:
                //nothing to do - unhandled input is ignored
        }
    }

    @Override
    public void update(ConsoleInterface consoleInterface) {
        //hack - test display! todo: other relevant information, stats, enemy info, etc.
        consoleInterface.drawFloor(Gamestate.getInstance().getFloor(), new Point());
    }
}
