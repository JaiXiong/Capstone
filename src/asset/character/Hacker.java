package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Hacker extends AbstractNonPlayerCharacter {

    public Hacker() {
        initiativeID=9;
        name = "hacker";
        leadName = "Undefined";
        xp=175;
        health = 50;
        maxHealth = 50;
        energy = 500;
        maxEnergy = 500;
        offenseA = 22.0;
        offenseB = 22.0;
        accuracy = 0.99;
        evade = 0.3;
        resistA = 0.2;
        resistB = 0.2;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {HACK, VIRUS};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.BLACK, '?');
    }

    @Override
    public String attackPC(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 75){
            energy-=75;
            return actions[0]; //HACK costs 75 energy
        }
        else if (toDo <= 1 && energy >= 50){
            energy-=50;
            return actions[1]; //VIRUS costs 50 energy
        }
        else return ATTACK;
    }
}
