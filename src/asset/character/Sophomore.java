package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Sophomore extends AbstractNonPlayerCharacter {

    public Sophomore() {
        initiativeID=2;
        name = "sophomore";
        leadName = "Sophomore";
        xp=70;
        health = 100;
        maxHealth = 100;
        energy = 100;
        maxEnergy = 100;
        offenseA = 12.0;
        offenseB = 17.0;
        accuracy = 0.7;
        evade = 0.85;
        resistA = 0.8;
        resistB = 0.8;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {BUCKLE_DOWN, STACK_OVERFLOW};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.BLUE, 'S');
    }

    @Override
    public String attack(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 15) return actions[0]; //BUCKLE_DOWN costs 15 energy
        else if (toDo <= 1 && energy >= 5) return actions[1]; //STACK_OVERFLOW costs 5 energy
        else return ATTACK;
    }
}
