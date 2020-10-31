package asset.character;

public class NPCFactory {

    /* Bosses have ids 11-14
     * regular baddies have ids 1-9
     * id 10 isn't used
     * id 0 is the player
     */
    public static AbstractNonPlayerCharacter npcLookup(int id){
        switch (id) {
            case 1:
                return new Freshman();
            default:
                return null;
        }
    }
}
