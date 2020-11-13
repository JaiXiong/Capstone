package engine;

import asset.character.*;
import asset.world.Terrain;

public class Actions {
    /* Actions should typically take actor and (if applicable) target parameters.
     * Actions always return a string for output to the player.
     */

    //Movement actions are handled directly in the engine. WAIT is a nonaction.

    /**
     * ATTACK
     * @param actor character performing action
     * @param target character receiving action
     * basic attack everyone has
     */
    public static String attack(AbstractCharacter actor, AbstractCharacter target){
        target.takeDamage(actor.getOffenseA(),"typeA",actor.getAccuracy());
        return (actor.getLeadName() + " attacks " + target.getName() + ".");
    }

    // Player (id 0) actions.

    /**
     * OPEN_DOOR is triggered by attempting to move into a locked door, it is not manually used
     * Because the OPEN_DOOR action is triggered unusually it does not follow the typical
     * (actor,target,etc) parameter structure
     * @param door DOOR tile attempting to open
     */
    public static String openDoor(Terrain door) {
        int code = door.getUnlockCode();
        if (Gamestate.getInstance().getPlayerCharacter().keyRing(code)) {
            door.unlock(code);
            return "You unlocked a door.";
        }
        return "You weren't able to unlock the door";
    }

    //Freshman (id 1) actions

    /**
     * HOMESICK
     * @param actor character performing action
     */
    public static String homesick(AbstractCharacter actor){
        actor.useEnergy(10);
        return (actor.getLeadName() + " is homesick.");
    }

    /**
     * TOO_MANY_MEMES
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String tooManyMemes(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(5);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " sends " + target.getName() + " too many memes.");
    }
}
