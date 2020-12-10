package asset.character;

import console.ConsoleGlyph;
import org.junit.*;


import static engine.ActionDefinitions.*;
import static org.junit.Assert.*;
public class Test_Hacker {
    @Test
    public void Test_MakeHacker(){
        Hacker p = new Hacker();
        assertEquals(p.getInitiativeID(), 9);
        assertEquals(p.getName(), "hacker");
        assertEquals(p.getLeadName(), "Undefined");
        assertEquals(p.getXP(), 175);
        assertEquals(p.getHealth(), 50);
        assertEquals(p.getMaxHealth(), 50);
        assertEquals(p.getEnergy(), 500);
        assertEquals(p.getMaxEnergy(), 500);
        assertEquals(p.getOffenseA(), 22.0, 0);
        assertEquals(p.getOffenseB(), 22.0, 0);
        assertEquals(p.getAccuracy(), 0.99, 0);
        assertEquals(p.getEvade(), 0.3, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.2, 0);
        assertEquals(p.getResistB(), 0.2, 0);
        String[] a = p.buildActions();
        assertEquals(a[0], HACK);
        assertEquals(a[1], VIRUS);
    }

    @Test
    public void test_get_console_glyph(){
        Hacker p = new Hacker();
        ConsoleGlyph g = p.getConsoleGlyph();
        assertNotNull(g);
    }

    @Test
    public void test_attackPC(){
        Hacker p = new Hacker();
        boolean check = false;
        String c = p.attackPC();
        if(c.equals(HACK) || c.equals(VIRUS) || c.equals(ATTACK)){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_Compare(){
        Hacker p = new Hacker();
        Freshman f  = new Freshman();
        int x = p.getInitiativeID()-f.getInitiativeID();
        assertEquals(x, p.compareTo(f));
    }

    @Test
    public void test_UseEnergy(){
        Hacker p = new Hacker();
        assertTrue(p.useEnergy(1));
        assertFalse(p.useEnergy(10000));
    }

}
