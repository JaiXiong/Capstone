package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Researcher extends AbstractNonPlayerCharacter {

    public Researcher() {
        initiativeID=8;
        name = "researcher";
        leadName = "Researcher";
        xp=175;
        health = 100;
        maxHealth = 100;
        energy = 250;
        maxEnergy = 250;
        offenseA = 15.0;
        offenseB = 23.0;
        accuracy = 0.98;
        evade = 0.5;
        resistA = 0.5;
        resistB = 0.5;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {SOFTWARE_DEV, MISS_SEMICOLON, PRESENTATION};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.WHITE, 'R');
    }

    @Override
    public String getNextAction(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 40) return actions[1];
        else if (toDo <= 1 && energy >= 20) return actions[0];
        else if(toDo <= 1 && energy>= 15) return actions[2];
        else return ATTACK;
    }
}
