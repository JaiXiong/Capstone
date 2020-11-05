package asset.character;


import console.ConsoleGlyph;
import org.junit.*;


import static engine.ActionDefinitions.*;
import static org.junit.Assert.*;
public class Test_Freshman {
    @Test
    public void Test_makeFreshman(){
        Freshman p = new Freshman();
        assertEquals(p.getInitiativeID(), 1);
        assertEquals(p.getName(), "freshman");
        assertEquals(p.getLeadName(), "Freshman");
        assertEquals(p.getXP(), 40);
        assertEquals(p.getHealth(), 70);
        assertEquals(p.getMaxHealth(), 70);
        assertEquals(p.getEnergy(), 70);
        assertEquals(p.getMaxEnergy(), 70);
        assertEquals(p.getOffenseA(), 10.0, 0);
        assertEquals(p.getOffenseB(), 12.0, 0);
        assertEquals(p.getAccuracy(), 0.5, 0);
        assertEquals(p.getEvade(), 0.95, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.95, 0);
        assertEquals(p.getResistB(), 0.95, 0);
        String[] a = p.buildActions();
        assertEquals(a[0], HOMESICK);
        assertEquals(a[1], TOO_MANY_MEMES);

    }

    @Test
    public void test_get_console_glyph(){
        Freshman p = new Freshman();
        ConsoleGlyph g = p.getConsoleGlyph();
        assertNotNull(g);
    }

    @Test
    public void test_get_action(){
        Freshman f = new Freshman();
        String[] a = f.buildActions();
        String[] actions = {MOVE_NORTH, MOVE_SOUTH, MOVE_EAST, MOVE_WEST, MOVE_NORTH_EAST, MOVE_NORTH_WEST, MOVE_SOUTH_EAST, MOVE_SOUTH_WEST, a[0],a[1]};
        boolean contains = false;
        String result = f.getNextAction();
        for (String action : actions) {
            if (action.equals(result)) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }
}
