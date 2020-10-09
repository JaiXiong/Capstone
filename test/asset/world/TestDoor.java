package asset.world;

import main.world.Door;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
public class TestDoor {
    @Test
    public void testDoorLock(){
        Door d = new Door(5,5, true);
        assertTrue(d.getLocked());
    }

    @Test
    public void testUnlockCode(){
        Door d = new Door(5,5, true);
        assertEquals(d.getUnlockCode(), -1);

    }

    @Test
    public void testUnlock1(){
        Door d = new Door(5,5, true);
        assertTrue(d.unlock(-1));

    }

    @Test
    public void testUnlock2(){
        Door d = new Door(5,5, true);
        assertFalse(d.unlock(1));

    }
}
