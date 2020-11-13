package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;

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
        offenseA = 30.0;
        offenseB = 30.0;
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
    public String getNextAction(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 55) return actions[0];
        else if (toDo <= 1 && energy >= 30) return actions[1];
        else if (toDo <= 1 && energy >= 25) return actions[2];
        else return ATTACK;
    }
}
