package asset.character;

import java.awt.*;

import static asset.items.Item.createItem;
import static engine.ActionDefinitions.*;
import static engine.Actions.pickupItem;

import console.ConsoleGlyph;
import engine.Gamestate;

public class TA extends AbstractNonPlayerCharacter {

    public TA() {
        initiativeID=5;
        name = "ta";
        leadName = "TA";
        xp=175;
        health = 200;
        maxHealth = 200;
        energy = 175;
        maxEnergy = 175;
        offenseA = 17.0;
        offenseB = 17.0;
        accuracy = 0.95;
        evade = 0.7;
        resistA = 0.65;
        resistB = 0.65;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {GRADE_HOMEWORK, DISCUSSION};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.ORANGE, 'T');
    }

    @Override
    public String attackPC(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 30){
            return actions[0]; //GRADE_HOMEWORK costs 30 energy
        }
        else if (toDo <= 1 && energy >= 20){
            return actions[1]; //DISCUSSION costs 20 energy
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
            case 5:
            case 4:
                pickupItem(createItem(104));
            case 3:
            case 2:
                pickupItem(createItem(102));
        }

        dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
                pickupItem(createItem(19));
                break;
            case 8:
                pickupItem(createItem(14));
                break;
            case 7:
                pickupItem(createItem(8));
                break;
            case 6:
                pickupItem(createItem(3));
        }
        return;
    }
}
