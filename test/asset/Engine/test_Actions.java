package asset.Engine;

import asset.character.Freshman;
import asset.character.PlayerCharacter;
import asset.character.Senior;
import engine.Gamestate;
import org.junit.Test;
import static org.junit.Assert.*;

import engine.Actions;
import asset.items.*;
import asset.world.*;

public class test_Actions {
    @Test
    public void test_attack(){
        PlayerCharacter p = new PlayerCharacter();
        Freshman f = new Freshman();
        String a = Actions.attack(p, f);
        String b = "You attacks freshman.";
        assertEquals(a, b);
    }

    @Test
    public void test_openDoor(){
        PlayerCharacter p = Gamestate.getInstance().getPlayerCharacter();
        Item i = new Item(100, "key", 0);
        p.addToInventory(i);
        Terrain d = new Terrain("test", null, 'd', 10, 10, true, 100 );
        assertEquals(Actions.openDoor(d), "You unlocked a door.");
        //Terrain d1 = new Terrain("test2", null, '1', 10, 10, true, 111);
        //assertEquals(Actions.openDoor(d1), "You weren't able to unlock the door.");
    }

    @Test
    public void test_equip(){

        Item i = Item.createItem(1);
        assertEquals(Actions.equip(i), "You've equipped Unsharpened Pencil.");

        i = Item.createItem(101);
        assertEquals(Actions.equip(i), "Can't equip Big Energy Drink.");
    }

    @Test
    public void test_discardItem(){
        PlayerCharacter p = Gamestate.getInstance().getPlayerCharacter();
        Item i = Item.createItem(101);
        p.addToInventory(i);
        assertEquals(Actions.discardItem(i), "Discarded " +i.getName() + ".");
        i = Item.createItem(102);
        assertEquals(Actions.discardItem(i), "You don't have one of those to drop.");

    }

    @Test
    public void test_useItem(){
        PlayerCharacter p = Gamestate.getInstance().getPlayerCharacter();
        Item i = Item.createItem(100);
        p.addToInventory(i);
        assertEquals(Actions.useItem(i), "You chug an Energy Drink.");

        i = Item.createItem(101);
        p.addToInventory(i);
        assertEquals(Actions.useItem(i), "You chug a Big Energy Drink, your heart races!");

        i = Item.createItem(102);
        p.addToInventory(i);
        assertEquals(Actions.useItem(i), "You drink some 'Juice' (21+).");

        i = Item.createItem(103);
        p.addToInventory(i);
        assertEquals(Actions.useItem(i), "You eat some Craft Dinner.");

        i = Item.createItem(104);
        p.addToInventory(i);
        assertEquals(Actions.useItem(i), "You eat Some Kinda Tex-Mex? Delish.");

        i = Item.createItem(1);
        p.addToInventory(i);
        assertEquals(Actions.useItem(i), "That's not an item you can use.");
    }
   @Test
    public void test_homesick(){
       PlayerCharacter p = new PlayerCharacter();
       String a = Actions.homesick(p);
       String b = "You is homesick.";
       assertEquals(a, b);
   }

   @Test
    public void test_tooManyMemes(){
       PlayerCharacter p = new PlayerCharacter();
       Freshman f = new Freshman();
       String a = Actions.tooManyMemes(p, f);
       String b = "You sends freshman too many memes.";
       assertEquals(a, b);
   }

   @Test
    public void test_buckleDown(){
        PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.buckleDown(p, s), "You buckles down.");
   }

   @Test
    public void test_stackOverflow(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.stackOverflow(p, s), "You posts senior's code on StackOverflow.");
   }

   @Test
    public void test_algorithms(){
        PlayerCharacter p = new PlayerCharacter();
        Senior s = new Senior();
        assertEquals(Actions.algorithms(p, s), "You complains to senior about algorithms.");
   }

   @Test
    public void test_debug(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.debug(p, s), "You makes senior debug their code.");
   }

   @Test
    public void test_finalProject(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.finalProject(p, s), "You asks senior to help work on their final project.");
   }

   @Test
   public void test_codeReview(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.codeReview(p, s), "You sends senior their code for a review.");
   }

   @Test
    public void test_gradeHomework(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.gradeHomework(p, s), "You grades senior's homework. Scary.");
   }

   @Test
    public void test_discussion(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.discussion(p, s), "You makes senior answer a discussion question.");
   }

   @Test
    public void test_assignHomework(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.assignHomework(p, s), "You assigns senior more homework.");
   }

   @Test
    public void test_livecode(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.liveCode(p, s), "You codes live. It doesn't compile...");
   }

   @Test
    public void test_accreditation(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.accreditation(p, s), "You asks senior to speak to the accreditation team.");
   }

   @Test
    public void test_techUpgrade(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.techUpgrade(p, s), "You upgrades their tech.");
   }

   @Test
    public void test_softwareDev(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.softwareDev(p, s), "You develops a new software.");
   }

   @Test
    public void test_missSemicolon(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.missSemicolon(p, s), "You distracts senior. Whoops. Missed a semicolon ;)");
   }

   @Test
    public void test_presentation(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.presentation(p, s), "You makes senior give a presentation.");
   }

   @Test
    public void test_hack(){
       PlayerCharacter p = new PlayerCharacter();
       Senior s = new Senior();
       assertEquals(Actions.hack(p, s), "You hacks senior's computer.");
   }

   @Test
    public void test_virus(){
        PlayerCharacter p = new PlayerCharacter();
        Senior s = new Senior();
        assertEquals(Actions.virus(p, s), "You sends senior a virus.");
    }
}
