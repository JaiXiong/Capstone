package engine;

public class ActionDefinitions {

    /**
     * Movement Actions
     */
    public static final String MOVE_NORTH = "moveNorth";
    public static final String MOVE_NORTH_EAST = "moveNorthEast";
    public static final String MOVE_EAST = "moveEast";
    public static final String MOVE_SOUTH_EAST = "moveSouthEast";
    public static final String MOVE_SOUTH = "moveSouth";
    public static final String MOVE_SOUTH_WEST = "moveSouthWest";
    public static final String MOVE_WEST = "moveWest";
    public static final String MOVE_NORTH_WEST = "moveNorthWest";

    /*
     * Wait in place one turn
     */
    public static final String WAIT = "wait";

    /*
     * Basic attack everyone has
     */
    public static final String ATTACK = "attack";

    /*
     * Player (id 0) actions
     */

    public static final String OPEN_DOOR = "openDoor";

    /*
     * Freshman (id 1) actions
     */
    public static final String TOO_MANY_MEMES = "tooManyMemes";
    public static final String HOMESICK = "homesick";
}
