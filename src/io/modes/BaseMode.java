package io.modes;

import engine.Engine;
import io.gui.ConsoleInterface;
import io.gui.GUIManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * The base mode, used to display splash screens.
 * On the first update call, display the startup splash screen.
 * On the first input, set the startup flag to false, then transition to the main menu.
 * On any subsequent calls, we have quit the game, so display the exit splash screen.
 * On any subsequent input, close the program.
 */
public class BaseMode extends IOMode {

    private boolean startup = true;

    @Override
    public void handle(KeyEvent ke) {
        if (startup) {
            GUIManager.getInstance().transitionTo(new GameplayMode()/* todo - implement main menu! new MainMenuMode()*/);
            Engine.getInstance().start(); //todo - this should go in MainMenuMode when we transition to GameplayMode
            startup = false;
        } else {
            System.exit(0);
        }
    }

    @Override
    public void update(ConsoleInterface consoleInterface) {
        if (startup) {
            consoleInterface.writeSingleLine(1, 1, "(Startup Splash Screen)", Color.BLUE);
        } else {
            consoleInterface.writeSingleLine(1, 1, "(Exit Splash Screen)");
        }
    }
}
