package asset.character;

import java.awt.*;

import static asset.items.Item.createItem;
import static engine.ActionDefinitions.*;
import static engine.Actions.pickupItem;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Senior extends AbstractNonPlayerCharacter {

    public Senior() {
        initiativeID=4;
        name = "senior";
        leadName = "Senior";
        xp=150;
        health = 150;
        maxHealth = 150;
        energy = 150;
        maxEnergy = 150;
        offenseA = 15.0;
        offenseB = 15.0;
        accuracy = 0.9;
        evade = 0.7;
        resistA = 0.7;
        resistB = 0.7;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {FINAL_PROJECT, CODE_REVIEW};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.RED, 'X');
    }

    @Override
    public String attackPC(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 20){
            energy-=20;
            return actions[0]; //FINAL_PROJECT costs 20 energy
        }
        else if (toDo <= 1 && energy >= 15){
            energy-=15;
            return actions[1]; //CODE_REVIEW costs 15 energy
        }
        else return ATTACK;
    }

    @Override
    public void die() {
        Gamestate.getInstance().getPlayerCharacter().gainXP(xp);
        Gamestate.getInstance().getPlayerCharacter().getKey(-1);

        int dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
            case 8:
            case 7:
                pickupItem(createItem(101));
                break;
            case 6:
                pickupItem(createItem(100));
                break;
            case 5:
                pickupItem(createItem(104));
                break;
            case 4:
            case 3:
                pickupItem(createItem(103));
                break;
            case 2:
            case 1:
                pickupItem(createItem(102));
        }

        dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
                pickupItem(createItem(20));
                break;
            case 8:
                pickupItem(createItem(14));
                break;
            case 7:
                pickupItem(createItem(7));
                break;
            case 6:
                pickupItem(createItem(3));
        }
        return;
    }
}
