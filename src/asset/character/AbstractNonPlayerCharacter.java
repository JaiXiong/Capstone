package asset.character;

import engine.Gamestate;

import java.awt.*;

import static engine.ActionDefinitions.*;

public abstract class AbstractNonPlayerCharacter extends AbstractCharacter{

    @Override
    public double getOffenseA() {
        return offenseA;
    }

    @Override
    public double getOffenseB() {
        return offenseB;
    }

    @Override
    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public double getEvade() {
        return evade;
    }

    @Override
    public double getResistA() {
        return resistA;
    }

    @Override
    public double getResistB() {
        return resistB;
    }

    public int getXP(){
        return xp;
    }

    public String attackPC() { return "default"; }

    /* Makes hostile NPCs stronger and more varied.
     * Improves a random stat, repeated five times,
     * re-randomizing the chosen stat each time.
     * Can be called repeatedly for serious business NPCs.
     */
    public void upgrade() {
        for (int i=0;i<5;i++){
            int randStat = (int)(Math.random()*8);
            xp = (int)(xp * 1.05);
            switch (randStat) {
                case 0:
                    health = (int)(health * 1.1);
                    maxHealth = (int)(maxHealth * 1.1);
                    break;
                case 1:
                    energy = (int)(energy * 1.05);
                    maxEnergy = (int)(energy * 1.05);
                    break;
                case 2:
                    offenseA = offenseA * 1.05;
                    break;
                case 3:
                    offenseB = offenseB * 1.05;
                    break;
                case 4:
                    accuracy = accuracy * 1.03;
                    break;
                case 5:
                    evade = evade * .98;
                    break;
                case 6:
                    resistA = resistA * .98;
                    break;
                case 7:
                    resistB = resistB * .98;
                    break;
            }
        }
    }

    /**
     * Basic movement system, inherited by all NPCs (keep here)
     * @return npc's next action name
     */
    @Override
    public String getNextAction(){
        Point pcWhere = Gamestate.getInstance().getPlayerCharacter().getLocation();
        double distance = location.distance(pcWhere);

        //if player is far, wander
        if (distance > 5) {
            int direction = (int)(Math.random()*16);
            switch (direction){
                case 0:
                    if(engine.Engine.getInstance().validateAction(this, MOVE_NORTH))
                        return MOVE_NORTH;
                case 1:
                    if(engine.Engine.getInstance().validateAction(this, MOVE_NORTH_EAST))
                        return MOVE_NORTH_EAST;
                case 2:
                    if(engine.Engine.getInstance().validateAction(this, MOVE_EAST))
                        return MOVE_EAST;
                case 3:
                    if(engine.Engine.getInstance().validateAction(this, MOVE_SOUTH_EAST))
                        return MOVE_SOUTH_EAST;
                case 4:
                    if(engine.Engine.getInstance().validateAction(this, MOVE_SOUTH))
                        return MOVE_SOUTH;
                case 5:
                    if(engine.Engine.getInstance().validateAction(this, MOVE_SOUTH_WEST))
                        return MOVE_SOUTH_WEST;
                case 6:
                    if(engine.Engine.getInstance().validateAction(this, MOVE_WEST))
                        return MOVE_WEST;
                case 7:
                    if(engine.Engine.getInstance().validateAction(this, MOVE_NORTH_WEST))
                        return MOVE_NORTH_WEST;
                default:
                    return WAIT;
            }
        }

        //TODO approaching can be smarter. Stretch goal.
        //if player is near, approach.
        else if (distance <= 5 && distance > 1) {
            if (location.x > pcWhere.x) {
                if (location.y > pcWhere.y && engine.Engine.getInstance().validateAction(this, MOVE_SOUTH_WEST)) {
                    return MOVE_SOUTH_WEST;
                }
                else if (location.y < pcWhere.y && engine.Engine.getInstance().validateAction(this, MOVE_NORTH_WEST)) {
                    return MOVE_NORTH_WEST;
                }
                else if (engine.Engine.getInstance().validateAction(this, MOVE_WEST)){
                    return MOVE_WEST;
                }
                else {
                    return WAIT;
                }
            }
            else if (location.x < pcWhere.x) {
                if (location.y > pcWhere.y && engine.Engine.getInstance().validateAction(this, MOVE_SOUTH_EAST)) {
                    return MOVE_SOUTH_EAST;
                }
                else if (location.y < pcWhere.y && engine.Engine.getInstance().validateAction(this, MOVE_NORTH_EAST)) {
                    return MOVE_NORTH_EAST;
                }
                else if (engine.Engine.getInstance().validateAction(this, MOVE_EAST)) {
                    return MOVE_EAST;
                }
                else{
                    return WAIT;
                }
            }
            else {
                if (location.y > pcWhere.y && engine.Engine.getInstance().validateAction(this, MOVE_SOUTH)) {
                    return MOVE_SOUTH;
                }
                else if (engine.Engine.getInstance().validateAction(this, MOVE_NORTH)) {
                    return MOVE_NORTH;
                }
                else {
                    return WAIT;
                }
            }
        }

        //if player is adjacent, do stuff
        else {
            return attackPC();
        }
    }
}
