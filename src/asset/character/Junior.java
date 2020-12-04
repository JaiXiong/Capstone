package asset.character;

import java.awt.*;

import static asset.items.Item.createItem;
import static engine.ActionDefinitions.*;
import static engine.Actions.pickupItem;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Junior extends AbstractNonPlayerCharacter {

    public Junior() {
        initiativeID=3;
        name = "junior";
        leadName = "Junior";
        xp=100;
        health = 120;
        maxHealth = 120;
        energy = 120;
        maxEnergy = 120;
        offenseA = 16.0;
        offenseB = 18.0;
        accuracy = 0.85;
        evade = 0.8;
        resistA = 0.8;
        resistB = 0.8;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {ALGORITHMS, DEBUG};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.GREEN, 'J');
    }

    @Override
    public String attackPC(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 20){
            return actions[0]; //ALGORITHMS costs 20 energy
        }
        else if (toDo <= 1 && energy >= 10){
            return actions[1]; //DEBUG costs 10 energy
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
                pickupItem(createItem(101));
                break;
            case 7:
                pickupItem(createItem(100));
                break;
            case 6:
                pickupItem(createItem(104));
                break;
            case 5:
            case 4:
                pickupItem(createItem(103));
                break;
            case 3:
                pickupItem(createItem(102));
        }

        dropRand = dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
                pickupItem(createItem(19));
                break;
            case 8:
                pickupItem(createItem(13));
                break;
            case 7:
                pickupItem(createItem(7));
                break;
            case 6:
                pickupItem(createItem(2));
        }
        return;
    }
}

