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
     * Ctrl-masked movement
     */
    public static final String CTRL_NORTH = "ctrlNorth";
    public static final String CTRL_NORTH_EAST = "ctrlNorthEast";
    public static final String CTRL_EAST = "ctrlEast";
    public static final String CTRL_SOUTH_EAST = "ctrlSouthEast";
    public static final String CTRL_SOUTH = "ctrlSouth";
    public static final String CTRL_SOUTH_WEST = "ctrlSouthWest";
    public static final String CTRL_WEST = "ctrlWest";
    public static final String CTRL_NORTH_WEST = "ctrlNorthWest";

    /*
     * Shift-masked movement
     */
    public static final String SHIFT_NORTH = "shiftNorth";
    public static final String SHIFT_NORTH_EAST = "shiftNorthEast";
    public static final String SHIFT_EAST = "shiftEast";
    public static final String SHIFT_SOUTH_EAST = "shiftSouthEast";
    public static final String SHIFT_SOUTH = "shiftSouth";
    public static final String SHIFT_SOUTH_WEST = "shiftSouthWest";
    public static final String SHIFT_WEST = "shiftWest";
    public static final String SHIFT_NORTH_WEST = "shiftNorthWest";

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

    //use an inventory item. This action must also include an index of the form NN -
    // for example, to use the third inventory item, we should send the action "useAt02"
    public static final String USE_AT = "useAt";

    //discard an inventory item. This action must also include an index of the form NN -
    // for example, to discard the third inventory item, we should send the action "discardAt02"
    public static final String DISCARD_AT = "discardAt";

    //use a special ability. This action must also include an index of the form NN -
    // for example, to use the third listed special, we should send the action "special02"
    public static final String SPECIAL = "special";

    //player special attacks
    public static final String HASKELL = "haskell";
    public static final String NULL_POINTER = "nullPointer";

    /*
     * Freshman (id 1) actions
     */
    public static final String TOO_MANY_MEMES = "tooManyMemes";
    public static final String HOMESICK = "homesick";

    public static final String BUCKLE_DOWN = "buckleDown";
    public static final String STACK_OVERFLOW = "stackOverflow";

    public static final String ALGORITHMS = "algorithms";
    public static final String DEBUG = "debug";

    public static final String FINAL_PROJECT = "finalProject";
    public static final String CODE_REVIEW = "codeReview";

    public static final String GRADE_HOMEWORK = "gradeHomework";
    public static final String DISCUSSION = "discussion";

    public static final String LECTURE = "lecture";
    public static final String ASSIGN_HOMEWORK = "assignHomework";
    public static final String LIVE_CODE = "liveCode";

    public static final String ACCREDITATION = "accreditation";
    public static final String COURSE_EVAL = "courseEval";
    public static final String TECH_UPGRADE = "techUpgrade";

    public static final String SOFTWARE_DEV = "softwareDev";
    public static final String MISS_SEMICOLON = "missSemicolon";
    public static final String PRESENTATION = "presentation";

    public static final String HACK = "hack";
    public static final String VIRUS = "virus";
}
