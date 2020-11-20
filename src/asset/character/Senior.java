package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;

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
        offenseA = 20.0;
        offenseB = 20.0;
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
}
