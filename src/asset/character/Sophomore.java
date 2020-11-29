package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;

import static asset.items.Item.createItem;
import static engine.ActionDefinitions.*;
import static engine.Actions.pickupItem;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Sophomore extends AbstractNonPlayerCharacter {

    public Sophomore() {
        initiativeID=2;
        name = "sophomore";
        leadName = "Sophomore";
        xp=70;
        health = 100;
        maxHealth = 100;
        energy = 100;
        maxEnergy = 100;
        offenseA = 12.0;
        offenseB = 17.0;
        accuracy = 0.7;
        evade = 0.85;
        resistA = 0.8;
        resistB = 0.8;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {BUCKLE_DOWN, STACK_OVERFLOW};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.BLUE, 'S');
    }

    @Override
    public String attackPC(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 15){
            energy-=15;
            return actions[0]; //BUCKLE_DOWN costs 15 energy
        }
        else if (toDo <= 1 && energy >= 5){
            energy-=5;
            return actions[1]; //STACK_OVERFLOW costs 5 energy
        }
        else return ATTACK;
    }

    @Override
    public void die() {
        Gamestate.getInstance().getPlayerCharacter().gainXP(xp);

        pickupItem(createItem(-1));

        int dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
            case 8:
            case 7:
            case 6:
                pickupItem(createItem(100));
                break;
            default:
                pickupItem(createItem(103));
        }

        dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
                pickupItem(createItem(18));
                break;
            case 8:
                pickupItem(createItem(17));
                break;
            case 7:
                pickupItem(createItem(12));
                break;
            case 6:
            case 5:
                pickupItem(createItem(6));
                break;
            case 4:
                pickupItem(createItem(1));
        }
        return;
    }
}
