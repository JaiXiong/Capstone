package asset.character;

import console.ConsoleGlyph;
import org.junit.*;


import static engine.ActionDefinitions.*;
import static org.junit.Assert.*;
public class Test_Sophomore {
    @Test
    public void Test_MakeSophomore(){
        Sophomore p = new Sophomore();
        assertEquals(p.getInitiativeID(), 2);
        assertEquals(p.getName(), "sophomore");
        assertEquals(p.getLeadName(), "Sophomore");
        assertEquals(p.getXP(), 70);
        assertEquals(p.getHealth(), 100);
        assertEquals(p.getMaxHealth(), 100);
        assertEquals(p.getEnergy(), 100);
        assertEquals(p.getMaxEnergy(), 100);
        assertEquals(p.getOffenseA(), 12.0, 0);
        assertEquals(p.getOffenseB(), 17.0, 0);
        assertEquals(p.getAccuracy(), 0.7, 0);
        assertEquals(p.getEvade(), 0.85, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.8, 0);
        assertEquals(p.getResistB(), 0.8, 0);
        String[] a = p.buildActions();
        assertEquals(a[0], BUCKLE_DOWN);
        assertEquals(a[1], STACK_OVERFLOW);
    }

    @Test
    public void test_get_console_glyph(){
        Sophomore p = new Sophomore();
        ConsoleGlyph g = p.getConsoleGlyph();
        assertNotNull(g);
    }

    @Test
    public void test_attackPC(){
        Sophomore p = new Sophomore();
        boolean check = false;
        String c = p.attackPC();
        if(c.equals(BUCKLE_DOWN) || c.equals(STACK_OVERFLOW) || c.equals(ATTACK)){
            check = true;
        }
        assertTrue(check);
    }

}
