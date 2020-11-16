package asset.character;

import engine.RNG;


public class NPCFactory {

    private static final int NPC_COUNT = 7;
    private static final int BOSS_COUNT = 2;

    /* Bosses have ids 11-14
     * regular baddies have ids 1-9
     * id 10 isn't used
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
                //todo - more regular NPCs
            case 11:
                return new Professor();
            case 12:
                return new Dean();
            default:
                throw new IllegalArgumentException("Unhandled ID: " + id);
        }
    }

    private static int randomID(int floorDifficulty) {
        int id = RNG.get().nextInt(NPC_COUNT) + 1;
        int variance;
        while (id > floorDifficulty) {
            variance = id - floorDifficulty;
            if (RNG.get().nextInt(variance * variance) + 1 == variance * variance) break; //small chance to generate an overpowered NPC
            id -= (variance / 2); //otherwise reduce the id by an amount corresponding to the variance
        }
        while (id < floorDifficulty) {
            variance = floorDifficulty - id;
            if (RNG.get().nextInt(variance) + 1 == variance) break; //moderate chance to generate an underpowered NPC
            id++; //otherwise increase the id by 1.
        }
        return id;
    }

    public static AbstractNonPlayerCharacter randomNPC(int floorDifficulty) {
        return npcLookup(randomID(floorDifficulty));
    }
}
