package engine;

import asset.character.*;

public class Actions {
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
