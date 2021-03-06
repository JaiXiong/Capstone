package engine;

import asset.character.*;
import asset.world.Terrain;
import asset.items.*;

import static engine.ActionDefinitions.*;

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
        //customize our message for the player if appropriate:
        boolean isPc0 = actor == Gamestate.getInstance().getPlayerCharacter();
        if(isPc0){
            target.takeDamage(actor.getOffenseA(), "typeA", actor.getAccuracy());
            return (
                    actor.getLeadName() +
                            " attack" + (isPc0 ? " " : "s ") +
                            target.getName() + ".");
        }
        else{
            //attack pc returns a string, which should then be used to call the correct action
            String attackType = ((AbstractNonPlayerCharacter) actor).attackPC();
            switch(attackType){
                case HOMESICK:
                    return homesick(actor);
                case TOO_MANY_MEMES:
                    return tooManyMemes(actor, target);
                case BUCKLE_DOWN:
                    return buckleDown(actor, target);
                case STACK_OVERFLOW:
                    return stackOverflow(actor, target);
                case ALGORITHMS:
                    return algorithms(actor, target);
                case DEBUG:
                    return debug(actor, target);
                case FINAL_PROJECT:
                    return finalProject(actor, target);
                case CODE_REVIEW:
                    return codeReview(actor, target);
                case GRADE_HOMEWORK:
                    return gradeHomework(actor, target);
                case DISCUSSION:
                    return discussion(actor, target);
                case LECTURE:
                    return lecture(actor, target);
                case ASSIGN_HOMEWORK:
                    return assignHomework(actor, target);
                case LIVE_CODE:
                    return liveCode(actor, target);
                case ACCREDITATION:
                    return accreditation(actor, target);
                case COURSE_EVAL:
                    return courseEval(actor, target);
                case TECH_UPGRADE:
                    return techUpgrade(actor, target);
                case SOFTWARE_DEV:
                    return softwareDev(actor, target);
                case MISS_SEMICOLON:
                    return missSemicolon(actor, target);
                case PRESENTATION:
                    return presentation(actor, target);
                case HACK:
                    return hack(actor, target);
                case VIRUS:
                    return virus(actor, target);
                default:
                    target.takeDamage(actor.getOffenseA(), "typeA", actor.getAccuracy());
                    return(
                            actor.getLeadName() +
                                    " attack" + (isPc0 ? " " : "s ") +
                                    target.getName() + ".");
            }
        }
    }

    /* Player (id 0) actions. Player actions generally don't have an 'actor' parameter
     * because the player is the assumed actor.
     */

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
        return "You weren't able to unlock the door.";
    }

    /**
     * EQUIP equips the selected item if able. Has no target parameter.
     * @param item the item to equip.
     * @return message for output to user
     */
    public static String equip(Item item) {
        if (item instanceof EquipableItem) {
            Gamestate.getInstance().getPlayerCharacter().equip((EquipableItem)item);
            return ("You've equipped " + item.getName() + ".");
        }
        return ("Can't equip " + item.getName() + ".");
    }

    /**
     * DROP removes the selected item from inventory to make space. Has no target parameter.
     * @param item to be removed
     * @return message for output to user
     */
    public static String discardItem(Item item) {
        if (item != null) {
            if (Gamestate.getInstance().getPlayerCharacter().removeFromInventory(item)) {
                return ("Discarded " + item.getName() + ".");
            }
        }
        return ("You don't have one of those to drop.");
    }

    /**
     * USE_ITEM expends the selected item to change health and energy values.
     * Has no target parameter.
     * @param item the item to use
     * @return message for output to user
     */
    public static String useItem(Item item) {
        PlayerCharacter you = Gamestate.getInstance().getPlayerCharacter();
        you.removeFromInventory(item);
        int dHealth = (int)item.getPrimaryValue();
        int dEnergy = 0 - (int)item.getSecondaryValue();
        if (dHealth != 0)
            you.takeDamage(dHealth, "heal", 1.0);
        if (dEnergy < 0)
            you.useEnergy(dEnergy);
        else if (dEnergy > 0 && dEnergy >= you.getEnergy())
            you.useEnergy(you.getEnergy());
        else if (dEnergy > 0)
            you.useEnergy(dEnergy);
        switch (item.getItemID()) {
            case 100: return "You chug an Energy Drink.";
            case 101: return "You chug a Big Energy Drink, your heart races!";
            case 102: return "You drink some 'Juice' (21+).";
            case 103: return "You eat some Craft Dinner.";
            case 104: return "You eat Some Kinda Tex-Mex? Delish.";
            default: throw new IllegalArgumentException("Unrecognized Item ID " + item.getItemID() + ".");
        }
    }

    /**
     * PICKUP adds the selected item to the player's inventory
     * This action should be triggered by walking over an item or by
     * defeating an NPC, it should not be callable directly by the player.
     * Has no target parameter.
     * Since this method will only be called by the player, and is called in a large number of places,
     * we generate the message here directly.
     * @param item item to add to the player's inventory
     */
    public static void pickupItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Called pickupItem with null argument.");
        } else {
            Messages.addMessage(
                    (Gamestate.getInstance().getPlayerCharacter().addToInventory(item)) ?
                            "Picked up a " + item.getName() + "." :
                            "Your backpack is full - discard or use an item to make room."
            );
        }
    }

    /**
     * Return whether the player can use the special ability at index.
     */
    public static boolean playerCanUseSpecial(int index) {
        PlayerCharacter pc = Gamestate.getInstance().getPlayerCharacter();
        if (pc.getActions()[index] == null) return false;
        switch (index) {
            case 0:
                return pc.getEnergy() > 50;
            case 1:
                return pc.getHealth() > pc.getMaxHealth() / 8;
            case 2:
                return pc.getEnergy() > 25;
            case 3:
                return pc.getEnergy() > pc.getMaxEnergy() / 4;
            default:
                throw new IllegalArgumentException("Tried to resolve unknown special attack " + index);
        }
    }

    public static String playerSpecial(int index, AbstractCharacter target) {
        if (Gamestate.getInstance().getPlayerCharacter().getActions()[index] == null)
            throw new IllegalArgumentException("Tried to resolve locked special attack " + index);
        PlayerCharacter pc = Gamestate.getInstance().getPlayerCharacter();
        int pcEnergy = pc.getEnergy();
        int val;
        switch (index) {
            case 0: //CRAM - consume all available energy to damage the target and heal the player, with boosted power for energy > 50
                pc.takeDamage(25 + (pcEnergy > 50 ? (pcEnergy - 50) / 2 : 0), "heal", 1.0);
                pc.useEnergy(pc.getEnergy());
                return ("You study furiously. You feel better.");
            case 1: //RIVALRY - consume 1/8th your maximum health and deal four times that as damage to your current target, stealing all its energy and gaining 4 times that.
                if (target == null) return "You have no rival. Choose one by attacking!";
                val = pc.getMaxHealth() / 8;
                pc.takeDamage(0 - val, "heal", 1.0);
                target.takeDamage(val * 4, "typeB", 1.0);
                pc.useEnergy(0 - target.getEnergy() * 4);
                int temp = target.getEnergy();
                target.useEnergy(temp);
                return temp > 0 ? "Your rival's energy drives you." : "Your rivalry harms you both.";
            case 2: //TOP OF THE CLASS - consume 25 energy, dealing damage to all NPCs as a percentage of their maximum.
                for (AbstractCharacter ac : Gamestate.getInstance().getCharacters()){
                    if (ac == pc) continue; //do not harm yourself
                    ac.takeDamage(ac.getMaxHealth() / 8.0, "typeB", 1.0);
                }
                pc.useEnergy(25);
                return "You strive to be the best. Your competitors falter.";
            case 3: //INVIGORATING FOCUS - consume 25% of your total energy, dealing damage, draining energy, and restoring health
                if (target == null) return "You have no rival to focus on!";
                if (target.getEnergy() == 0) return "This rival is not worth your focus.";
                pc.useEnergy(pc.getMaxEnergy() / 4);
                val = Math.min(target.getEnergy(), 10);
                target.useEnergy(val);
                target.takeDamage(4 * val, "typeB", 1.0);
                pc.takeDamage(2 * val, "heal", 1.0);
                return target.getEnergy() > 0 ? "Your rival seems drained, while you are invigorated." : "Your rival has been drained of all its energy.";
            default:
                throw new IllegalArgumentException("Tried to resolve unknown special attack " + index);
        }
    }

    /**
     * NULL_POINTER
     * @param actor character performing the action(player)
     * @param target character targeted by the action(adjacent NPC)
     * @return message for output to user
     */
    public static String nullPointer(AbstractCharacter actor, AbstractCharacter target){
        if (actor.useEnergy(5)) {
            target.takeDamage((actor.getOffenseB() + 10), "typeB", actor.getAccuracy());
            return ("You point out " + target.getLeadName() + "'s null pointer errors.");
        }
        return ("Not enough energy to do that!");
    }

    /**
     * HASKELL
     * @param actor character performing the action(player)
     * @param target character targeted by the action(adjacent NPC)
     * @return message for output to user
     */
    public static String haskell(AbstractCharacter actor, AbstractCharacter target){
        if (actor.getEnergy() >= 15) {
            target.takeDamage((actor.getOffenseB() + actor.getOffenseA() + 5), "typeB", actor.getAccuracy());
            actor.useEnergy(15);
            return ("You make a joke about Haskell. " + target.getLeadName() + " groans.");
        }
        return ("Not enough energy to do that!");
    }

    //NPC actions

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
        boolean isPc0 = actor == Gamestate.getInstance().getPlayerCharacter();
        actor.useEnergy(5);
        return (actor.getLeadName() + " posts " + target.getName() + (isPc0 ? "r " : "'s ") + "code on StackOverflow.");
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
        boolean isPc0 = actor == Gamestate.getInstance().getPlayerCharacter();
        actor.useEnergy(30);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " grades " + target.getName() + (isPc0 ? "r " : "'s ") + "homework. Scary.");
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
        boolean isPc0 = actor == Gamestate.getInstance().getPlayerCharacter();
        actor.useEnergy(75);
        target.takeDamage(actor.getOffenseB(),"typeB",actor.getAccuracy());
        return (actor.getLeadName() + " hacks " + target.getName() + (isPc0 ? "r " : "'s ") + "computer.");
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
