package io.gui;

import asset.character.AbstractCharacter;
import asset.character.PlayerCharacter;
import asset.world.Floor;
import asset.world.Terrain;
import console.Console;
import engine.Gamestate;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Wrap the Console and provide implementation specific methods.
 */
public class ConsoleInterface {
    private final Console CONSOLE;

    public ConsoleInterface(KeyListener keyListener){
        CONSOLE = new Console(24, 64, new Dimension(20, 32));
        CONSOLE.addKeyListener(keyListener);
    }

    public void clearScreen() {
        CONSOLE.clearScreen();
    }

    public void updateScreen() {
        CONSOLE.refresh();
    }

    private Point getConsoleCenterpoint() {
        Dimension d = CONSOLE.getSize();
        return new Point(d.width / 2, d.height / 2);
    }

    public void drawFloor() {
        Floor floor = Gamestate.getInstance().getFloor();
        PlayerCharacter playerCharacter = Gamestate.getInstance().getPlayerCharacter();
        Point playerAt = playerCharacter.getLocation();
        Point center = getConsoleCenterpoint();
        final int cOffset = center.x - playerAt.x;
        final int rOffset = center.y - playerAt.y;
        Terrain terrain;
        int cFloor;
        int rFloor;
        for (int r = 0; r < CONSOLE.getSize().getHeight(); ++r) {
            for (int c = 0; c < CONSOLE.getSize().getWidth(); ++c) {
                cFloor = c - cOffset;
                rFloor = r - rOffset;
                if (cFloor < 0 || rFloor < 0 || cFloor >= floor.getColumns() || rFloor >= floor.getRows()) continue; //out of floor bounds
                terrain = floor.getTerrainAt(rFloor, cFloor);
                CONSOLE.update(r, c, terrain.getConsoleGlyph());
            }
        }
    }
    public void drawCharacters() {
        PlayerCharacter playerCharacter = Gamestate.getInstance().getPlayerCharacter();
        Point playerAt = playerCharacter.getLocation();
        Point center = getConsoleCenterpoint();
        final int cOffset = center.x - playerAt.x;
        final int rOffset = center.y - playerAt.y;
        for (AbstractCharacter character : Gamestate.getInstance().getCharacters()) {
            Point at = character.getLocation();
            int c = at.x + cOffset;
            int r = at.y + rOffset;
            if (r < 0 || r >= CONSOLE.getSize().getHeight() || c < 0 || c >= CONSOLE.getSize().getWidth())
                continue;
            CONSOLE.update(r, c, character.getConsoleGlyph());
        }
    }

    public Dimension getConsoleSize() {
        return CONSOLE.getSize();
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
    public int writeSingleLine(int row, int originColumn, String text) {
        return writeSingleLine(row, originColumn, text, new Color[0]);
    }

    public int writeSingleLine(int row, int originColumn, String text, Color... colorOverrides) {
        int currentColumn = -1;
        for (int i = 0; i < text.length(); ++i) {
            currentColumn = originColumn + i;
            if (CONSOLE.validatePosition(row, currentColumn))
                CONSOLE.update(row, currentColumn, text.charAt(i), colorOverrides);
            else break;
        }
        return (currentColumn < CONSOLE.getSize().width) ? currentColumn : -1;
    }
}
