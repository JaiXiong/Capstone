package asset.world;

import main.world.Terrain;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
public class TestTerrain {
    @Test
    public void testTerrain1(){
        Terrain t = new Terrain("Terrain", Color.CYAN, 10, 10 );
        assertEquals(t.getType(), "Terrain");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getX(), 10);
        assertEquals(t.getY(), 10);
        //looks like terrain still in progress
        //assertEquals(t.getBackgroundColor(), Color)
    }
}
