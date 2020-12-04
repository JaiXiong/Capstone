package asset.character;

import java.awt.*;

import static asset.items.Item.createItem;
import static engine.ActionDefinitions.*;
import static engine.Actions.pickupItem;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Professor extends AbstractNonPlayerCharacter {

    public Professor() {
        initiativeID=6;
        name = "professor";
        leadName = "Professor";
        xp=175;
        health = 300;
        maxHealth = 300;
        energy = 225;
        maxEnergy = 225;
        offenseA = 25.0;
        offenseB = 25.0;
        accuracy = 0.97;
        evade = 0.6;
        resistA = 0.6;
        resistB = 0.6;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {LECTURE, ASSIGN_HOMEWORK, LIVE_CODE};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.MAGENTA, 'P');
    }

    @Override
    public String attackPC(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 45){
            return actions[0]; //LECTURE costs 45 energy
        }
        else if (toDo <= 1 && energy >= 25){
            return actions[1]; //ASSIGN_HOMEWORK costs 25 energy
        }
        else if (toDo <= 2 && energy >= 20){
            return actions[2]; //LIVE_CODE costs 20 energy
        }
        else return ATTACK;
    }

    @Override
    public void die() {
        Gamestate.getInstance().getPlayerCharacter().gainXP(xp);
        Gamestate.getInstance().getPlayerCharacter().getKey(200);

        pickupItem(createItem(101));
        pickupItem(createItem(104));

        int dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
                pickupItem(createItem(21));
                break;
            case 8:
                pickupItem(createItem(20));
                break;
            case 7:
                pickupItem(createItem(15));
                break;
            case 6:
                pickupItem(createItem(14));
                break;
            case 5:
                pickupItem(createItem(10));
                break;
            case 4:
                pickupItem(createItem(8));
                break;
            case 3:
                pickupItem(createItem(5));
                break;
            case 2:
                pickupItem(createItem(4));
                break;
            case 1:
                pickupItem(createItem(3));
        }
        return;
    }
}
