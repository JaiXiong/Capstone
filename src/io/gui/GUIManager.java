package io.gui;

import io.modes.BaseMode;
import io.modes.IOMode;
import io.modes.MainMenuMode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

/**
 * Provides access to the Console.
 * Implemented as a singleton class so that we do not need to pass references to it - it can be accessed from
 * anywhere via GUIManager.getInstance().
 * Modes are implemented in a stack, and the mode at the top of the stack is used for drawing the screen.
 * With this design, we can easily revert to a previous mode when we are done with the one we're using -
 * for example, exiting the main menu will revert to the base mode, used for the entry and exit splash screens,
 * exiting the game will revert to the main menu, so that the player may start a new game or exit the program,
 * and exiting something like an inventory screen or a message recall screen will return to the main game mode.
 */
public class GUIManager implements KeyListener {

    private static GUIManager instance = null;

    private final ConsoleInterface CONSOLE_INTERFACE = new ConsoleInterface(this);
    private final Stack<IOMode> MODE_STACK = new Stack<>();

    //keep access private - only our static getInstance method is allowed to construct a new GUIManager
    private GUIManager() {}

    /**
     * Provides global access to the singleton instance.
     * If none exists, we create it here and initialize as necessary - it persists for the lifetime of the game process.
     */
    public static GUIManager getInstance() {
        if (instance == null) {
            instance = new GUIManager();
            instance.transitionTo(new BaseMode());
        }
        return instance;
    }

    /**
     * Leave the current IOMode and revert to the previous IOMode - this destroys the current mode.
     */
    public void revert() {
        CONSOLE_INTERFACE.clearScreen();
        MODE_STACK.pop();
        //ensure saved game information is accurate
        if (MODE_STACK.peek() instanceof MainMenuMode) {
            MODE_STACK.pop();
            MODE_STACK.push(new MainMenuMode());
        }
        updateScreen();
    }

    /**
     * Leave the current IOMode and transition to a new IOMode - this preserves the current mode for later use.
     */
    public void transitionTo(IOMode nextMode) {
        CONSOLE_INTERFACE.clearScreen();
        MODE_STACK.push(nextMode);
        updateScreen();
    }

    /**
     * Update the Console based on the current IOMode.
     */
    public void updateScreen() {
        MODE_STACK.peek().update(CONSOLE_INTERFACE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //nothing to do here
    }

    @Override
    public void keyPressed(KeyEvent e) {
        MODE_STACK.peek().handle(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //nothing to do here
    }
}
