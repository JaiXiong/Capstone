package io.modes;

import asset.character.PlayerCharacter;
import asset.items.Item;
import engine.ActionDefinitions;
import engine.Engine;
import engine.Gamestate;
import io.gui.GUIManager;

import java.awt.*;
import java.util.ArrayList;

public class DiscardItemSelectMenuMode extends AbstractInventorySelectMenuMode {
    public DiscardItemSelectMenuMode() {
        super("Select an item to discard:",
                Color.BLACK,
                Color.WHITE,
                Color.GREEN,
                Color.BLACK,
                Color.BLACK,
                Color.GREEN,
                Color.BLACK);
    }

    @Override
    public void execute() {
        PlayerCharacter pc = Gamestate.getInstance().getPlayerCharacter();
        ArrayList<Item> inv = pc.getInventory();
        if (selectedOption < inv.size()) {//if selectedOption is not CANCEL
            //queue a use action for the selected item
            pc.setNextAction(ActionDefinitions.DISCARD_AT + (selectedOption > 9 ? selectedOption : "0" + selectedOption));
            Engine.getInstance().setPlayerHasActionQueued();
        }
        GUIManager.getInstance().revert(); //close the menu
    }
}
