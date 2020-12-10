package asset.character;

import asset.AbstractGameAsset;
import java.awt.*;

public abstract class AbstractCharacter extends AbstractGameAsset implements Comparable<AbstractCharacter>{

    /* to avoid initiative collision, each initiativeID should be unique,
     * which also lets us use it as a character hash
     */
    int initiativeID;
    String name,leadName;

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

    // percentage stats
    // higher is better for offense
    double offenseA;
    double offenseB;
    double accuracy;
    //lower is better for defense
    double evade;
    double resistA;
    double resistB;

    Point location = null;

    //this character's current target
    private AbstractCharacter target = null;

    //TODO buildActions may not be necessary anymore

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

    public String getName(){
        return name;
    }

    public String getLeadName(){
        return leadName;
    }

    public int getHealth(){
        return health;
    }

    public int getMaxHealth() { return maxHealth; }

    public int getEnergy(){
        return energy;
    }

    public int getMaxEnergy() { return maxEnergy; }

    /**
     * @param amount how much energy spent
     * returns true if valid change, false if invalid
     */
    public boolean useEnergy(int amount) {
        if ((amount <= energy && amount >= 0) || (amount < 0 && (energy - maxEnergy < amount))) {
            energy -= amount;
            return true;
        }
        else return false;
    }

    /* the remaining stats can be modified by persistant
     * effects, and have to calculate a total value based
     * off the fields
     */
    public abstract double getOffenseA();

    public abstract double getOffenseB();

    public abstract double getAccuracy();

    public abstract double getEvade();

    public abstract double getResistA();

    public abstract double getResistB();

    /* @param amount how much unmitigated damage is being dealt
     * @param type which resistance applies to the damage
     * @param chanceToHit the attacker's accuracy
     * actions that do damage to a character call that character's
     * takeDamage to modify that character's health.
     */
    public void takeDamage(double amount, String type, double chanceToHit){
        double resist;
        double modifiedEvade = getEvade();
        switch (type) {
            case "typeA":
                resist = getResistA();
                break;
            case "typeB":
                resist = getResistB();
                break;
            case "heal":
                resist = -1.0;
                modifiedEvade = 1.0;
                chanceToHit = 1.0;
                break;
            default:
                resist = 1.0;
        }
        if (Math.random() < modifiedEvade * chanceToHit) {
            health -= (int)(amount * resist);
            if (health <= 0) {
                health = 0;
            }
            else if (health > maxHealth) {
                health = maxHealth;
            }
        }
    }

    public abstract void die();

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public AbstractCharacter getTarget() {
        if (target != null && target.getHealth() <= 0)
            setTarget(null); //target is dead, clear it
        return target;
    }

    public void setTarget(AbstractCharacter target) {
        this.target = target;
    }

    abstract public String getNextAction();

    @Override
    /* Characters that compareTo==0 aren't necessarily equal,
     * they have the same ID and originate from the same template
     */
    public int compareTo(AbstractCharacter other) {
        return getInitiativeID() - other.getInitiativeID();
    }
}
