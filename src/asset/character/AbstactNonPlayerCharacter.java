package asset.character;

public abstract class AbstactNonPlayerCharacter extends AbstractCharacter{

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
    public int getMoveRate() {
        return moveRate;
    }

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

    @Override
    public double getResistC() {
        return resistC;
    }

    @Override
    public double getResistD() {
        return resistD;
    }

    public int getXP(){
        return xp;
    }

    //TODO
    /* this method scripts what actions an NPC
     * takes on their turn
     */
    public void executeTurn(){
    }

    //TODO add some other actions
}
