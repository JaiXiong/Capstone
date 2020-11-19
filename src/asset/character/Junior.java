package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;

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
    public String getNextAction(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 10) return actions[0]; //HOMESICK costs 10 energy
        else if (toDo <= 1 && energy >= 5) return actions[1]; //TOO_MANY_MEMES costs 5 energy
        else return ATTACK;
    }
}

