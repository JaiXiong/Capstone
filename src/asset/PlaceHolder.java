package asset;
import asset.character.AbstractNonPlayerCharacter;
import java.util.ArrayList;
import asset.character.NPCFactory;
import java.util.Collections;


//TODO this code is not used, and is only maintained for reference.
public class PlaceHolder {

    //assumes 8 regular floors with 4 evenly spaced boss floors

    /* @param the number floor being populated, used to seed random generation
     * This method generates and sorts the NPCs for a floor, which can
     * then be added to the initiative ArrayList already containing the player
     * at index 0
     */
    public ArrayList<AbstractNonPlayerCharacter> populateFloor(int floorNum){
        ArrayList npcs = new ArrayList<AbstractNonPlayerCharacter>();

        //if we're on a boss floor generation isn't random
        if (floorNum % 3 == 0) {
            npcs.add(NPCFactory.npcLookup((floorNum/3)+10));
        }

        else {
            //how many NPCs to place on floor, between 5 and 8, trends up with floor level
            int howMany = (int)((Math.random() * 3) + (floorNum/3) + 3);

            for (int i=0;i<howMany;i++){
                /* which type of NPC to add, not every type is available on every floor
                 * Floor 1-2 have ids 1-6 available, Floor 4-5 have ids 2-7
                 * Floor 7-8 have ids 3-8, Floor 10-11 have ids 4-9
                 */
                int select = (int)((Math.random() * 6) + (floorNum/3) + 1);
                AbstractNonPlayerCharacter current = NPCFactory.npcLookup(select);

                /* whether to upgrade the NPC, trending up with floor level
                 * lower floors can't upgrade, higher ones guarantee upgrading
                 */
                if (((Math.random() * 6) + floorNum - 7) > 0) {
                    //how many times to upgrade, trending up with floor level
                    int upgrades = (int) ((Math.random() * (floorNum/3)) + 1);
                    for (int j = 0; j < upgrades; j++) {
                        current.upgrade();
                    }
                }

                npcs.add(current);
            }

            Collections.sort(npcs);
        }

        return npcs;
    }

}
