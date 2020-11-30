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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            f.makeFloor("testinvalid", Color.CYAN, 10 , 10 );
        });

        String expectedMessage = "Unrecognized type: testinvalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


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
        assertFalse(f.isTerrainPassableAt(5,5));

        t = f.makeFloor("terrain", null, 10 , 10 );
        f.setTerrainAt(3, 3, t);
        assertTrue(f.isTerrainPassableAt(3,3));

        t = f.makeFloor("wall", Color.CYAN, 10 , 10 );
        f.setTerrainAt(2, 7, t);
        assertFalse(f.isTerrainPassableAt(2,7));
    }

    @Test
    public void test_makeFloor(){
        Floor f = new Floor(10, 10);
        Terrain t = f.makeFloor("terrain", null, 10 , 10);
        assertEquals(t.getType(), "terrain");
        assertNull(t.getColor());
        assertEquals(t.getSymbol(), ' ');

        t = f.makeFloor("wall", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "wall");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '#');

        t = f.makeFloor("door", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "door");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '+');

        t = f.makeFloor("tree", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "tree");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), 'Ÿ');

        t = f.makeFloor("table", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "table");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '╥');

        t = f.makeFloor("water", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "water");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '≈');

        t = f.makeFloor("toiletright", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "toiletright");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '╛');

        t = f.makeFloor("toiletleft", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "toiletleft");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '╘');

        t = f.makeFloor("lightpost1", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "lightpost1");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), 'Î');

        t = f.makeFloor("lightpost2", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "lightpost2");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '¶');


        t = f.makeFloor("grass", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "grass");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '“');

        t = f.makeFloor("cementedwalkway", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "cementedwalkway");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '░');

        t = f.makeFloor("cloverplant", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "cloverplant");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '♣');

        t = f.makeFloor("spadeplant", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "spadeplant");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '♠');

        t = f.makeFloor("solidwall", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "solidwall");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '▓');

        t = f.makeFloor("divider", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "divider");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '|');

        t = f.makeFloor("shelf", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "shelf");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '=');

        t = f.makeFloor("chairleft", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "chairleft");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), 'L');

        t = f.makeFloor("staircase", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "staircase");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '>');

        t = f.makeFloor("emergency exit", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "emergency exit");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '<');

        t = f.makeFloor("chairright", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "chairright");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '┘');

        t = f.makeFloor("windowcross", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "windowcross");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '┼');

        t = f.makeFloor("windowhoriztonalbar", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "windowhoriztonalbar");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '═');

        t = f.makeFloor("windowverticalcross", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "windowverticalcross");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '║');

        t = f.makeFloor("windowleftbottomcornercross", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "windowleftbottomcornercross");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '╚');

        t = f.makeFloor("windowrightbottomcornercross", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "windowrightbottomcornercross");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '╝');

        t = f.makeFloor("windowleftopcornercross", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "windowleftopcornercross");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '╔');

        t = f.makeFloor("windowrighttopcornercross", Color.CYAN, 10 , 10);
        assertEquals(t.getType(), "windowrighttopcornercross");
        assertEquals(t.getColor(), Color.CYAN);
        assertEquals(t.getSymbol(), '╗');

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            f.makeFloor("testinvalid", Color.CYAN, 10, 10);
        });

        String expectedMessage = "Unrecognized type: testinvalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


    }
}
