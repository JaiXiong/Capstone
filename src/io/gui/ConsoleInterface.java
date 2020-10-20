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
     * Find the best row to start a block of text so that it appears centered.
     * @param blockHeight the height of the block of text
     * @return the row to start the block on
     */
    public int getVerticalCenter(int blockHeight) {
        int h0 = CONSOLE.getSize().height;
        int dh = h0 - blockHeight;
        if (dh <= 0) throw new IllegalArgumentException("Input text too large to center on the Console.");
        return dh / 2;
    }

    /**
     * Write a string centered on the console at the specified row. Strings exceeding the available size will
     * cause an IllegalArgumentException to be thrown.
     */
    public void writeCenteredLine(int row, String text) throws IllegalArgumentException{
        writeCenteredLine(row, text, new Color[0]);
    }

    public void writeCenteredLine(int row, String text, Color... colorOverrides) throws IllegalArgumentException {
        int w0 = CONSOLE.getSize().width;
        int w1 = text.length();
        int dw = w0 - w1;
        if (dw <= 0) throw new IllegalArgumentException("Input text too large to center on the Console.");
        writeSingleLine(row, dw / 2, text, colorOverrides);
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
