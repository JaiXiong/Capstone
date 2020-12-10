package asset.character;

import console.ConsoleGlyph;
import org.junit.*;


import static engine.ActionDefinitions.*;
import static org.junit.Assert.*;
public class Test_TA {
    @Test
    public void Test_MakeTA(){
        TA p = new TA();
        assertEquals(p.getInitiativeID(), 5);
        assertEquals(p.getName(), "ta");
        assertEquals(p.getLeadName(), "TA");
        assertEquals(p.getXP(), 175);
        assertEquals(p.getHealth(), 200);
        assertEquals(p.getMaxHealth(), 200);
        assertEquals(p.getEnergy(), 175);
        assertEquals(p.getMaxEnergy(), 175);
        assertEquals(p.getOffenseA(), 22.0, 0);
        assertEquals(p.getOffenseB(), 22.0, 0);
        assertEquals(p.getAccuracy(), 0.95, 0);
        assertEquals(p.getEvade(), 0.7, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.65, 0);
        assertEquals(p.getResistB(), 0.65, 0);
        String[] a = p.buildActions();
        assertEquals(a[0], GRADE_HOMEWORK);
        assertEquals(a[1], DISCUSSION);
    }

    @Test
    public void test_get_console_glyph(){
        TA p = new TA();
        ConsoleGlyph g = p.getConsoleGlyph();
        assertNotNull(g);
    }

    @Test
    public void test_attackPC(){
        TA p = new TA();
        boolean check = false;
        String c = p.attackPC();
        if(c.equals(GRADE_HOMEWORK) || c.equals(DISCUSSION) || c.equals(ATTACK)){

            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_Compare(){
        TA p = new TA();
        Freshman f  = new Freshman();
        int x = p.getInitiativeID()-f.getInitiativeID();
        assertEquals(x, p.compareTo(f));
    }

    @Test
    public void test_UseEnergy(){
        TA p = new TA();
        assertTrue(p.useEnergy(1));
        assertFalse(p.useEnergy(10000));
    }
}
