package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;

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
}
