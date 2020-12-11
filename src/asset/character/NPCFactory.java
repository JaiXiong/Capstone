package asset.character;

import engine.RNG;


public class NPCFactory {

    private static final int NPC_COUNT = 7;
    private static final int BOSS_COUNT = 2;

    /* Bosses have ids 11-14
     * regular baddies have ids 1-7
     * 8-10 not used
     */
    public static AbstractNonPlayerCharacter npcLookup(int id){
        switch (id) {
            case 1:
                return new Freshman();
            case 2:
                return new Sophomore();
            case 3:
                return new Junior();
            case 4:
                return new Senior();
            case 5:
                return new Hacker();
            case 6:
                return new TA();
            case 7:
                return new Researcher();
            case 11:
                return new Professor();
            case 12:
                return new Dean();
            default:
                throw new IllegalArgumentException("Unhandled ID: " + id);
        }
    }

    private static int randomID(int floorDifficulty) {
        int id = (floorDifficulty / 3) + 1;
        while (RNG.get().nextInt(100) + 1 > 90 - (floorDifficulty * floorDifficulty)) {
            if (++id > floorDifficulty + Math.log(floorDifficulty) || id > 6) break;
        }
        return id;
    }

    public static AbstractNonPlayerCharacter randomNPC(int floorDifficulty) {
        return npcLookup(randomID(floorDifficulty));
    }
}
