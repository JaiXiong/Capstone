package asset.Engine;

import asset.character.Freshman;
import asset.character.PlayerCharacter;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
import engine.Actions;


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
}
