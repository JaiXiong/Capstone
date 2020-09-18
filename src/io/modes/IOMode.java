package io.modes;

import main.Console;

/**
 * IOMode corresponds to a game state such as the start screen, main game screen, inventory screen, etc.
 * Implementing classes must properly draw the screen for that game state, drawing on necessary information like
 * the current level map, the player's stats and inventory, visible monsters on the screen, or whether or not a
 * saved game exists.
 * They must also properly handle player input for the corresponding gamestate - for example, pressing the arrow keys
 * might move the player on the main screen, or scroll through options on a menu screen.
 */
public abstract class IOMode {
    public abstract void update(Console console);
    //todo - handle input as well!
}
