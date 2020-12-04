package io.modes;

import asset.items.Item;
import engine.Gamestate;
import io.gui.ConsoleInterface;

import java.awt.*;
import java.util.ArrayList;

/**
 * Create a menu containing each item in the player's inventory as a MenuOption.
 * Menus for using and dropping items should extend this.
 */
public abstract class AbstractInventorySelectMenuMode extends MenuMode {

    private static final MenuOption CANCEL = new MenuOption("<Cancel>", true);

    private Color[] ITEM_FOREGROUND_COLORS;

    /**
     * @param menuName the title or prompt for the menu
     * @param colors   - a color or null to use default
     *                 0: Title background
     *                 1: Title foreground
     *                 2: Selected option background
     *                 3: Selected option foreground
     *                 4: Available option background
     *                 5: Available option foreground
     *                 6: Disabled option background
     *                 7: Disabled option foreground
     */
    public AbstractInventorySelectMenuMode(String menuName, Color... colors) {
        super(menuName, colors, buildOptions());
        ArrayList<Item> playerInventory = Gamestate.getInstance().getPlayerCharacter().getInventory();
        ITEM_FOREGROUND_COLORS = new Color[playerInventory.size()];
        int index = 0;
        for (Item item : playerInventory) {
            ITEM_FOREGROUND_COLORS[index++] = item.getDisplayColor();
        }
    }

    private static MenuOption[] buildOptions() {
        ArrayList<Item> inv = Gamestate.getInstance().getPlayerCharacter().getInventory();
        MenuOption[] opts;
        if (inv.size() == 0) {
            opts = new MenuOption[2];
            opts[0] = new MenuOption("<no items in inventory>", false);
        } else {
            opts = new MenuOption[inv.size() + 1];
            for (int i = 0; i < inv.size(); ++i) {
                opts[i] = new MenuOption(inv.get(i).getDisplayText(), true);
            }
        }
        opts[opts.length - 1] = CANCEL;
        return opts;
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
                                    new Color[]{COLORS[BG_SEL],
                                            i < ITEM_FOREGROUND_COLORS.length ?
                                                    ITEM_FOREGROUND_COLORS[i] :
                                                    COLORS[FG_SEL]} :
                                    new Color[]{COLORS[BG_EN],
                                            i < ITEM_FOREGROUND_COLORS.length ?
                                                    ITEM_FOREGROUND_COLORS[i] :
                                                    COLORS[FG_EN]} :
                            new Color[]{COLORS[BG_DIS], COLORS[FG_DIS]}
            );
        }
        consoleInterface.updateScreen();
    }
}
