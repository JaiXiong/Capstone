package asset.Engine;

import engine.Gamestate;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Gamestate {
    @Test
    public void test_gs1(){
        assertNotNull(Gamestate.getInstance());
    }
}
