package io.gui;

import console.Console;

import java.awt.*;

/**
 * Wrap the Console and provide implementation specific methods.
 */
public class ConsoleInterface {
    private final Console CONSOLE;

    public ConsoleInterface(){
        CONSOLE = new Console(24, 64, new Dimension(9, 16));
    }

    /**
     * Write a string as image data on the canvas. Strings exceeding the available size will be cut off.
     * These methods do not change the image visible on the screen.
     */
    public void writeSingleLine(int row, int originColumn, String text) {
        writeSingleLine(row, originColumn, text, new Color[0]);
    }

    public void writeSingleLine(int row, int originColumn, String text, Color... colorOverrides) {
        int currentColumn;
        for (int i = 0; i < text.length(); ++i) {
            currentColumn = originColumn + i;
            if (CONSOLE.validatePosition(row, currentColumn))
                CONSOLE.update(row, currentColumn, text.charAt(i), colorOverrides);
            else break;
        }
        CONSOLE.refresh();
    }
}
