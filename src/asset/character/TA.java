package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;

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
        offenseA = 22.0;
        offenseB = 22.0;
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
    public String getNextAction(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 30) return actions[0];
        else if (toDo <= 1 && energy >= 20) return actions[1];
        else return ATTACK;
    }
}
