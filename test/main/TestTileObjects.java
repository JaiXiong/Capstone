package main;

import main.world.TileObjects;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;

public class TestTileObjects {
    @Test
    public void testTiles1(){
        TileObjects t = new TileObjects("Dungeon", Color.CYAN, 10, 10 );
        assertEquals(t.getType(), "Dungeon");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getX(), 10);
        assertEquals(t.getY(), 10);
    }
}
