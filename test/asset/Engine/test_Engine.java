package asset.Engine;

import asset.character.Freshman;
import asset.character.PlayerCharacter;
import engine.Engine;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
import engine.Actions;
public class test_Engine {
    @Test
    public void test_getInstance(){
        assertNotNull(Engine.getInstance());
    }

}
