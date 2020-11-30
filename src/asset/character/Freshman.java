package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;
import static engine.Actions.pickupItem;
import static asset.items.Item.createItem;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Freshman extends AbstractNonPlayerCharacter {

    public Freshman() {
        initiativeID=1;
        name = "freshman";
        leadName = "Freshman";
        xp=40;
        health = 70;
        maxHealth = 70;
        energy = 70;
        maxEnergy = 70;
        offenseA = 10.0;
        offenseB = 12.0;
        accuracy = 0.5;
        evade = 0.95;
        resistA = 0.95;
        resistB = 0.95;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {HOMESICK,TOO_MANY_MEMES};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.YELLOW, 'F');
    }

    @Override
    public String attackPC(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 10){
            return actions[0]; //HOMESICK costs 10 energy
        }
        else if (toDo <= 1 && energy >= 5){
            return actions[1]; //TOO_MANY_MEMES costs 5 energy
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
            case 6:
                pickupItem(createItem(100));
                break;
            case 5:
            case 4:
            case 3:
            case 2:
                pickupItem(createItem(103));
        }

        dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
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