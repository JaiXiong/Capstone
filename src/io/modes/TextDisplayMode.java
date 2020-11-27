package io.modes;

import io.gui.ConsoleInterface;
import io.gui.GUIManager;

import java.awt.event.KeyEvent;

public class TextDisplayMode extends IOMode {

    private final String[] TEXT;

    public TextDisplayMode(String... text) {
        TEXT = text;
    }

    @Override
    public void handle(KeyEvent ke) {
        GUIManager.getInstance().revert(); // on any key press, return to the previous mode.
    }

    @Override
    public void update(ConsoleInterface consoleInterface) {
        for (int i = 0; i < TEXT.length; ++i)
            consoleInterface.writeSingleLine(i + 1, 1, TEXT[i]);
        consoleInterface.updateScreen();
    }
}
