package asset.character;

import asset.character.AbstractNonPlayerCharacter;
import java.awt.*;
import static engine.ActionDefinitions.*;

import console.ConsoleGlyph;
import engine.Gamestate;

public class Freshman extends AbstractNonPlayerCharacter {

    public Freshman() {
        initiativeID=1;
        name = "freshman";
        leadName = "Freshman";
        xp=40;
        health = 70;
        maxHealth = 70;
        energy = 70;
        maxEnergy = 70;
        offenseA = 10.0;
        offenseB = 12.0;
        accuracy = 0.5;
        evade = 0.95;
        resistA = 0.95;
        resistB = 0.95;
        actions = buildActions();
    }

    @Override
    public String[] buildActions() {
        return new String[] {HOMESICK,TOO_MANY_MEMES};
    }

    @Override
    public ConsoleGlyph getConsoleGlyph() {
        return new ConsoleGlyph(null, Color.YELLOW, 'F');
    }

    @Override
    public String getNextAction(){
        Point pcWhere = Gamestate.getInstance().getPlayerCharacter().getLocation();
        double distance = location.distance(pcWhere);

        //if player is far, wander
        if (distance > 5) {
            int direction = (int)(Math.random()*16);
            switch (direction){
                case 0:
                    return MOVE_NORTH;
                case 1:
                    return MOVE_NORTH_EAST;
                case 2:
                    return MOVE_EAST;
                case 3:
                    return MOVE_SOUTH_EAST;
                case 4:
                    return MOVE_SOUTH;
                case 5:
                    return MOVE_SOUTH_WEST;
                case 6:
                    return MOVE_WEST;
                case 7:
                    return MOVE_NORTH_WEST;
                default:
                    return WAIT;
            }
        }

        //TODO the way this handles approaching the player is super dumb
        //if player is near, approach
        else if (distance <= 5 && distance > 1) {
            if (location.x > pcWhere.x) {
                if (location.y > pcWhere.y) {
                    return MOVE_SOUTH_WEST;
                }
                else if (location.y < pcWhere.y) {
                    return MOVE_NORTH_WEST;
                }
                else {
                    return MOVE_WEST;
                }
            }
            else if (location.x < pcWhere.x) {
                if (location.y > pcWhere.y) {
                    return MOVE_SOUTH_EAST;
                }
                else if (location.y < pcWhere.y) {
                    return MOVE_NORTH_EAST;
                }
                else {
                    return MOVE_EAST;
                }
            }
            else {
                if (location.y > pcWhere.y) {
                    return MOVE_SOUTH;
                }
                else {
                    return MOVE_NORTH;
                }
            }
        }

        //if player is adjacent, do stuff
        else {
            int toDo = (int)(Math.random()*4);
            if (toDo == 0 && energy >= 10) return actions[0]; //HOMESICK costs 10 energy
            else if (toDo <= 1 && energy >= 5) return actions[1]; //TOO_MANY_MEMES costs 5 energy
            else return ATTACK;
        }
    }
}