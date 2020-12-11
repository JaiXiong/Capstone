package asset.character;

import java.awt.*;

import static asset.items.Item.createItem;
import static engine.ActionDefinitions.*;
import static engine.Actions.pickupItem;

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
        offenseA = 10.0;
        offenseB = 18.0;
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
    public String attackPC(){
        int toDo = (int)(Math.random()*4);
        if (toDo == 0 && energy >= 40){
            return actions[1]; //SOFTWARE_DEV costs 40 energy
        }
        else if (toDo <= 1 && energy >= 20){
            return actions[0]; //MISS_SEMICOLON costs 20 energy
        }
        else if(toDo <= 2 && energy>= 15){
            return actions[2]; //PRESENTATION costs 15 energy
        }
        else return ATTACK;
    }

    @Override
    public void die() {
        Gamestate.getInstance().getPlayerCharacter().gainXP(xp);
        Gamestate.getInstance().getPlayerCharacter().getKey(-1);

        int dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
                pickupItem(createItem(101)); //intentional fallthrough for double drop
            case 8:
            case 7:
            case 6:
                pickupItem(createItem(101));
                break;
            case 5:
                pickupItem(createItem(104)); //intentional fallthrough for double drop
            case 4:
            case 3:
            case 2:
                pickupItem(createItem(104));
            case 1:
                pickupItem(createItem(102));
        }

        dropRand = (int)(Math.random() * 10);
        switch (dropRand) {
            case 9:
                pickupItem(createItem(21));
                break;
            case 8:
                pickupItem(createItem(15));
                break;
            case 7:
                pickupItem(createItem(10));
                break;
            case 6:
                pickupItem(createItem(4));
        }
        return;
    }
}
