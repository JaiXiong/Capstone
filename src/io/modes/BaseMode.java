package io.modes;

import engine.Engine;
import engine.Gamestate;
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
            GUIManager.getInstance().transitionTo(new MainMenuMode());
            startup = false;
            Gamestate.clearInstance(); //clear the instance we used to draw the splash screen floor
        } else {
            System.exit(0);
        }
    }

    @Override
    public void update(ConsoleInterface consoleInterface) {
        if (startup) {
            Gamestate.getInstance().SplashScreen("Depth-First Search and Destroy");
            consoleInterface.drawFloor();
        } else {
            Gamestate.getInstance().SplashScreen("Thanks for playing!");
            consoleInterface.drawFloor();
        }
        consoleInterface.updateScreen();
    }
}
