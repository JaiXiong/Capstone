package asset.world;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;

public class TestFloor {
    @Test
    public void testFloor1(){
        Floor f = new Floor(10, 10);
        Terrain t = f.makeFloor("terrain", Color.CYAN, 10 , 10 );
        assertEquals(t.getType(), "terrain");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getX(), 10);
        assertEquals(t.getY(), 10);
    }
    @Test
    public void testFloor2(){
        Floor f = new Floor(10, 10);
        Terrain t = f.makeFloor("wall", Color.CYAN, 10 , 10 );
        assertEquals(t.getType(), "wall");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getX(), 10);
        assertEquals(t.getY(), 10);

    }
    @Test
    public void testFloor3(){
        Floor f = new Floor(10, 10);
        Terrain t = f.makeFloor("door", Color.CYAN, 10 , 10 );
        assertEquals(t.getType(), "door");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getX(), 10);
        assertEquals(t.getY(), 10);
    }

    @Test
    public void testFloor4(){
        Floor f = new Floor(10, 10);
        assertNull(f.makeFloor("testinvalid", Color.CYAN, 10 , 10 ));
    }
}
