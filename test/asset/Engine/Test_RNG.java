package asset.Engine;

import engine.RNG;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_RNG {
    @Test
    public void test_RNG(){
        assertNotNull(RNG.get());
    }
}
