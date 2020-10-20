package io.modes;

import engine.Engine;
import io.gui.GUIManager;

import java.awt.*;

public class MainMenuMode extends MenuMode {

    private final int OPT_IDX_NEW_GAME = 0;
    private final int OPT_IDX_LOAD_GAME = 1;
    private final int OPT_IDX_EXIT = 2;

    public MainMenuMode() {
        super(
                "Welcome to <untitled roguelike>!",
                new Color[] {
                     null,
                     Color.BLUE,
                     Color.GREEN,
                     null,
                     null,
                     Color.GREEN,
                     null,
                     Color.DARK_GRAY
                },
                new MenuOption("Begin New Game", true),
                new MenuOption("Load Existing Game", false),
                new MenuOption("Exit", true)
                //todo - enable or disable based on existence of save file
        );
    }

    @Override
    public void execute() {
        switch (selectedOption) {
            case OPT_IDX_NEW_GAME:
                //todo - initialize a new game
                startGame();
                break;
            case OPT_IDX_LOAD_GAME:
                //todo - load the existing save file
                startGame();
                break;
            case OPT_IDX_EXIT:
                GUIManager.getInstance().revert();
                break;
            default:
                throw new IllegalStateException("Option index " + selectedOption + " not handled.");
        }
    }

    private void startGame() {
        GUIManager.getInstance().transitionTo(new GameplayMode());
        Engine.getInstance().start();
    }
}
