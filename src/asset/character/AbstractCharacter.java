package asset.character;

import asset.AbstractGameAsset;

public abstract class AbstractCharacter extends AbstractGameAsset {

    /* characters may need the information on how they display
     * (which char, what color, what background) included in
     * the character class
     */

    /* to avoid initiative collision, each initiativeID should be unique,
     * which also lets us use it as a character hash
     */
    int initiativeID;

    /* contains all the actions a given character has available
     * beyond the handful of default actions
     */
    String[] actions;

    /* all characters have XP, but it works different
     * for the player than for NPCs. NPC XP doesn't
     * change, but player XP increases based on the
     * XP of NPCs they defeat. Only needs a getter
     * for NPCs.
     */
    int xp;

    // basic character stats
    int health;
    int maxHealth;
    int energy;
    int maxEnergy;
    int moveRate;

    // percentage stats
    // higher is better for offense
    double offenseA;
    double offenseB;
    double accuracy;
    //lower is better for defense
    double evade;
    double resistA;
    double resistB;
    double resistC;
    double resistD;


    /* helper method for building the actions list so its
     * not all crammed in the constructor
     */
    abstract String[] buildActions();


    /* initiativeID shouldn't be modified
     * health and energy should only be modified by events,
     * not constant modifiers, so we can put the getters here
     */
    public int getInitiativeID(){
        return initiativeID;
    }

    public int getHealth(){
        return health;
    }

    public int getMaxHealth() { return maxHealth; }

    public int getEnergy(){
        return energy;
    }

    public int getMaxEnergy() { return maxEnergy; }

    /* the remaining stats can be modified by persistant
     * effects, and have to calculate a total value based
     * off the fields
     */
    abstract int getMoveRate();

    abstract double getOffenseA();

    abstract double getOffenseB();

    abstract double getAccuracy();

    abstract double getEvade();

    abstract double getResistA();

    abstract double getResistB();

    abstract double getResistC();

    abstract double getResistD();

    //TODO needs to be filled in
    /* @param amount how much unmitigated damage is being dealt
     * @param type which resistance applies to the damage
     * @param chanceToHit the attacker's accuracy
     * actions that do damage to a character call that character's
     * takeDamage to modify that character's health.
     */
    public void takeDamage(int amount, String type, double chanceToHit){
        if (Math.random() < evade * chanceToHit) {
            double resist;
            switch (type) {
                case "typeA":
                    resist = resistA;
                    break;
                case "typeB":
                    resist = resistB;
                    break;
                case "typeC":
                    resist = resistC;
                    break;
                case "typeD":
                    resist = resistD;
                    break;
                case "heal":
                    resist = -1.0;
                    break;
                default:
                    resist = 1.0;
            }
            health -= (int)(amount * resist);
            if (health <= 0) health = 0; //TODO implement game over
        }
    }

    //TODO move to child classes, amount is calculated slightly differently for each
    /*TODO fill in amount calculation, figure out if targetting needs to be
     * within the method or a seperate method.
     */
    /* @param the character getting hit
     * calculates base damage and calls takeDamage
     */
    public void basicOffense(AbstractCharacter target){
        int amount = 0;
        target.takeDamage(amount, "typeA", accuracy);
    }

    //TODO needs to be filled in
    /* action that moves the character
     *
     */
    public void move(){
    }

    //TODO possibly implement persistant effects (buffs/debuffs, damage-over-time, etc)

    //TODO possibly implement interaction with non-character objects like doors
}
