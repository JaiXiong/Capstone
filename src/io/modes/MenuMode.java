package io.modes;

import io.gui.ConsoleInterface;
import io.gui.GUIManager;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/**
 * Implement a menu which supports keyboard scrolling and selection.
 */
public abstract class MenuMode extends IOMode {

    public static class MenuOption {
        public final String text;
        public final boolean enabled;

        MenuOption(String text, boolean enabled) {
            this.text = text;
            this.enabled = enabled;
        }
    }

    protected static final int BG_TTL = 0;
    protected static final int FG_TTL = 1;
    protected static final int BG_SEL = 2;
    protected static final int FG_SEL = 3;
    protected static final int BG_EN = 4;
    protected static final int FG_EN = 5;
    protected static final int BG_DIS = 6;
    protected static final int FG_DIS = 7;


    protected final String MENU_NAME;
    protected final Color[] COLORS;
    protected final MenuOption[] OPTS;

    protected int selectedOption = -1;
    protected final int ENABLED_OPTION_COUNT;

    /**
     * @param menuName the title or prompt for the menu
     * @param colors - a color or null to use default
     *               0: Title background
     *               1: Title foreground
     *               2: Selected option background
     *               3: Selected option foreground
     *               4: Available option background
     *               5: Available option foreground
     *               6: Disabled option background
     *               7: Disabled option foreground
     * @param opts - a variable sized array of options
     */
    public MenuMode(
        String menuName,
        Color[] colors,
        MenuOption... opts
    ) {
        MENU_NAME = menuName;
        COLORS = colors;
        OPTS = opts;
        int enabledCount = 0;
        for (int i = 0; i < OPTS.length; ++i) {
            MenuOption mo = OPTS[i];
            if (mo.enabled) {
                if (selectedOption < 0)
                    selectedOption = i;
                ++enabledCount;
            }
        }
        ENABLED_OPTION_COUNT = enabledCount;
    }

    public abstract void execute();

    @Override
    public void handle(KeyEvent ke) {
        int code = ke.getKeyCode();
        switch (code) {
            case VK_UP:
                if (ENABLED_OPTION_COUNT > 0) {
                    do {
                        if (--selectedOption < 0) selectedOption = OPTS.length - 1;
                    } while (!OPTS[selectedOption].enabled);
                }
                GUIManager.getInstance().updateScreen();
                break;
            case VK_DOWN:
                if (ENABLED_OPTION_COUNT > 0) {
                    do {
                        if (++selectedOption >= OPTS.length) selectedOption = 0;
                    } while (!OPTS[selectedOption].enabled);
                }
                GUIManager.getInstance().updateScreen();
                break;
            case VK_ESCAPE:
                GUIManager.getInstance().revert();
                break;
            case VK_ENTER:
                if (!OPTS[selectedOption].enabled)
                    throw new IllegalStateException("Option index " + selectedOption + " not enabled.");
                execute();
                break;
            default:
                //nothing to do, invalid input
        }
    }

    @Override
    public void update(ConsoleInterface consoleInterface) {
        int row = consoleInterface.getVerticalCenter(OPTS.length + 2);
        consoleInterface.writeCenteredLine(row, MENU_NAME, new Color[]{COLORS[BG_TTL], COLORS[FG_TTL]});
        row += 2;
        for (int i = 0; i < OPTS.length; ++i) {
            MenuOption mo = OPTS[i];
            consoleInterface.writeCenteredLine(
                    row++,
                    mo.text,
                    mo.enabled ?
                            i == selectedOption ?
                                    new Color[]{COLORS[BG_SEL], COLORS[FG_SEL]} :
                                    new Color[]{COLORS[BG_EN], COLORS[FG_EN]} :
                            new Color[]{COLORS[BG_DIS], COLORS[FG_DIS]}
            );
        }
        consoleInterface.updateScreen();
    }
}
