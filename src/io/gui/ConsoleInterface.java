package io.gui;

import asset.world.Floor;
import console.Console;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Wrap the Console and provide implementation specific methods.
 */
public class ConsoleInterface {
    private final Console CONSOLE;

    public ConsoleInterface(KeyListener keyListener){
        CONSOLE = new Console(24, 64, new Dimension(9, 16));
        CONSOLE.addKeyListener(keyListener);
    }

    public void clearScreen() {
        CONSOLE.clearScreen();
    }

    public void drawFloor(Floor floor, Point playerAt) {
        for (int i = 0; i < floor.getRows(); ++i) {
            for (int j = 0; j < floor.getColumns(); ++j) {
                CONSOLE.update(i, j, floor.getTerrainAt(i, j).getConsoleGlyph());
            }
        }
        CONSOLE.refresh();
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
