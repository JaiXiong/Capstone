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
}
