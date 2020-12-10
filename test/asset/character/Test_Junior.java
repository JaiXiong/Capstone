package asset.character;

import console.ConsoleGlyph;
import org.junit.*;


import static engine.ActionDefinitions.*;
import static org.junit.Assert.*;
public class Test_Junior {
    @Test
    public void Test_MakeJunior(){
        Junior p = new Junior();
        assertEquals(p.getInitiativeID(), 3);
        assertEquals(p.getName(), "junior");
        assertEquals(p.getLeadName(), "Junior");
        assertEquals(p.getXP(), 100);
        assertEquals(p.getHealth(), 120);
        assertEquals(p.getMaxHealth(), 120);
        assertEquals(p.getEnergy(), 120);
        assertEquals(p.getMaxEnergy(), 120);
        assertEquals(p.getOffenseA(), 16.0, 0);
        assertEquals(p.getOffenseB(), 18.0, 0);
        assertEquals(p.getAccuracy(), 0.85, 0);
        assertEquals(p.getEvade(), 0.8, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.8, 0);
        assertEquals(p.getResistB(), 0.8, 0);
        String[] a = p.buildActions();
        assertEquals(a[0], ALGORITHMS);
        assertEquals(a[1], DEBUG);
    }

    @Test
    public void test_get_console_glyph(){
        Junior p = new Junior();
        ConsoleGlyph g = p.getConsoleGlyph();
        assertNotNull(g);
    }

    @Test
    public void test_attackPC(){
        Junior p = new Junior();
        boolean check = false;
        String c = p.attackPC();
        if(c.equals(ALGORITHMS) || c.equals(DEBUG) || c.equals(ATTACK)){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_Compare(){
        Junior p = new Junior();
        Freshman f  = new Freshman();
        int x = p.getInitiativeID()-f.getInitiativeID();
        assertEquals(x, p.compareTo(f));
    }
}
