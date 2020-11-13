package asset.world;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;

public class TestFloor {
    @Test
    public void testFloor1(){
        Floor f = new Floor(10, 10);
        Terrain t = f.makeFloor("terrain", null, 10 , 10 );
        assertEquals(t.getType(), "terrain");
        assertNull(t.getColor());
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

    @Test
    public void test_FloorGetTerrainAt(){
        //todo when terrain[x][y] is known and populated
        Floor f = new Floor(10, 10);
        Terrain t = f.makeFloor("door", Color.CYAN, 10 , 10 );
        f.setTerrainAt(5, 5, t);
        boolean check = false;
        if(f.getTerrainAt(5, 5) == t){
            check = true;
        }
        assertTrue(check);
    }
    @Test
    public void test_FloorGetTerrainType (){
        //todo when terrain[x][y] is known and populated
        Floor f = new Floor(10, 10);
        Terrain t = f.makeFloor("door", Color.CYAN, 10 , 10 );
        f.setTerrainAt(5, 5, t);
        boolean check = false;
        if(f.getTerrainType(5, 5).equals("door")){
            check = true;
        }
        assertTrue(check);

        t = f.makeFloor("terrain", null, 10 , 10 );
        f.setTerrainAt(3, 3, t);
        check = f.getTerrainType(3, 3).equals("terrain");
        assertTrue(check);

        t = f.makeFloor("wall", Color.CYAN, 10 , 10 );
        f.setTerrainAt(2, 7, t);
        check = f.getTerrainType(2, 7).equals("wall");
        assertTrue(check);
    }

    @Test
    public void test_FloorTerrainIsPassable(){
        //todo when terrain[x][y] is known and populated
        Floor f = new Floor(10, 10);
        Terrain t = f.makeFloor("door", Color.CYAN, 10 , 10 );
        f.setTerrainAt(5, 5, t);
        assertTrue(f.isTerrainPassableAt(5,5));

        t = f.makeFloor("terrain", null, 10 , 10 );
        f.setTerrainAt(3, 3, t);
        assertTrue(f.isTerrainPassableAt(3,3));

        t = f.makeFloor("wall", Color.CYAN, 10 , 10 );
        f.setTerrainAt(2, 7, t);
        assertFalse(f.isTerrainPassableAt(2,7));
    }
}
