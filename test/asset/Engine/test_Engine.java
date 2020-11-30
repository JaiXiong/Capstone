package asset.Engine;


import engine.Engine;

import org.junit.Test;
import static org.junit.Assert.*;

public class test_Engine {
    @Test
    public void test_getInstance(){
        assertNotNull(Engine.getInstance());
    }
}
