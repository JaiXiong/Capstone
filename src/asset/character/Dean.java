package asset.character;

import java.awt.*;

import static asset.items.Item.createItem;
import static engine.ActionDefinitions.*;
import static engine.Actions.pickupItem;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Dean extends AbstractNonPlayerCharacter {

    public Dean() {
        initiativeID=7;
        name = "dean";
        leadName = "Dean";
        xp=175;
        health = 350;
        maxHealth = 350;
        energy = 250;
        maxEnergy = 250;
        offenseA = 25.0;
        offenseB = 25.0;
        accuracy = 0.98;
        evade = 0.65;
        resistA = 0.55;
        resistB = 0.55;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {ACCREDITATION, COURSE_EVAL, TECH_UPGRADE};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.CYAN, 'D');
    }

    @Override
    public String attackPC(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 55){
            return actions[0]; //ACCREDITATION costs 55 energy
        }
        else if (toDo <= 1 && energy >= 30){
            return actions[1]; //COURSE_EVAL costs 30 energy
        }
        else if (toDo <= 2 && energy >= 25){
            return actions[2]; //TECH_UPGRADE costs 25 energy
        }
        else return ATTACK;
    }

    @Override
    public void die() {
        Gamestate.getInstance().getPlayerCharacter().gainXP(xp);
        Gamestate.getInstance().getPlayerCharacter().getKey(201);

        pickupItem(createItem(101));
        pickupItem(createItem(101));
        pickupItem(createItem(104));
        pickupItem(createItem(104));

        pickupItem(createItem(22));
        pickupItem(createItem(16));

        if ((Math.random() * 10) > 9) pickupItem(createItem(5));

        return;
    }
}
