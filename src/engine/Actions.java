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
    public static String attack(AbstractCharacter actor, AbstractCharacter target) {
        target.takeDamage(actor.getOffenseA(), "typeA", actor.getAccuracy());
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

    /**
     * BUCKLE_DOWN
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String buckleDown(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(15);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " buckles down.");
    }

    /**
     * STACK_OVERFLOW
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String stackOverflow(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(5);
        return (actor.getLeadName() + " posts " + target.getName() + "'s code on StackOverflow.");
    }

    /**
     * ALGORITHMS
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String algorithms(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(20);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " complains to " + target.getName() + " about algorithms.");
    }

    /**
     * DEBUG
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String debug(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(10);
        target.takeDamage(actor.getOffenseA(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " makes " + target.getName() + " debug their code.");
    }

    /**
     * FINAL_PROJECT
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String finalProject(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(20);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " asks " + target.getName() + " to help work on their final project.");
    }

    /**
     * CODE_REVIEW
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String codeReview(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(15);
        target.takeDamage(actor.getOffenseA(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " sends " + target.getName() + " their code for a review.");
    }

    /**
     * GRADE_HOMEWORK
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String gradeHomework(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(30);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " grades " + target.getName() + "'s homework. Scary.");
    }

    /**
     * DISCUSSION
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String discussion(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(20);
        target.takeDamage(actor.getOffenseA(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " makes " + target.getName() + " answer a discussion question.");
    }

    /**
     * LECTURE
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String lecture(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(45);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " lectures " + target.getName() + ".");
    }

    /**
     * ASSIGN_HOMEWORK
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String assignHomework(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(25);
        target.takeDamage(actor.getOffenseA(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " assigns " + target.getName() + " more homework.");
    }

    /**
     * LIVE_CODE
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String liveCode(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(20);
        return (actor.getLeadName() + " codes live. It doesn't compile...");
    }

    /**
     * ACCREDITATION
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String accreditation(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(55);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " asks " + target.getName() + " to speak to the accreditation team.");
    }

    /**
     * COURSE_EVAL
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String courseEval(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(30);
        target.takeDamage(actor.getOffenseA(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " makes " + target.getName() + " fill out a course eval.");
    }

    /**
     * TECH_UPGRADE
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String techUpgrade(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(25);
        return (actor.getLeadName() + " upgrades their tech.");
    }

    /**
     * SOFTWARE_DEV
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String softwareDev(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(40);
        return (actor.getLeadName() + " develops a new software.");
    }

    /**
     * MISS_SEMICOLON
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String missSemicolon(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(20);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " distracts " + target.getName() + ". Whoops. Missed a semicolon ;)");
    }

    /**
     * PRESENTATION
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String presentation(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(15);
        target.takeDamage(actor.getOffenseA(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " makes " + target.getName() + " give a presentation.");
    }

    /**
     * HACK
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String hack(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(75);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " hacks " + target.getName() + "'s computer.");
    }

    /**
     * VIRUS
     * @param actor character performing action
     * @param target character receiving action
     */
    public static String virus(AbstractCharacter actor, AbstractCharacter target){
        actor.useEnergy(50);
        target.takeDamage(actor.getOffenseA(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " sends " + target.getName() + " a virus.");
    }

}
