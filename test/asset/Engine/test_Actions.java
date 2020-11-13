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

    // TODO: 11/5/2020 add more tests for actions
}
