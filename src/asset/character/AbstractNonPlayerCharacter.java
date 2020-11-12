package asset.character;

public abstract class AbstractNonPlayerCharacter extends AbstractCharacter{

    /* characters may need the information on how they display
     * (which char, what color, what background) included in
     * the character class
     */

    //TODO
    /* for now the getters will just give raw stats
     * but if we add persistant effects we'll have to
     * figure those in
     */
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
    public String attack() { return "default"; }

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
            attack();
        }
    }

    //TODO
    /* this method scripts what actions an NPC
     * takes on their turn
     */
    public void executeTurn(){
    }

    //TODO add some other actions
}
