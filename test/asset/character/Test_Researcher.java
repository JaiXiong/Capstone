package asset.character;

import console.ConsoleGlyph;
import org.junit.*;


import static engine.ActionDefinitions.*;
import static org.junit.Assert.*;
public class Test_Researcher {
    @Test
    public void Test_MakeResearcher(){
        Researcher p = new Researcher();
        assertEquals(p.getInitiativeID(), 8);
        assertEquals(p.getName(), "researcher");
        assertEquals(p.getLeadName(), "Researcher");
        assertEquals(p.getXP(), 175);
        assertEquals(p.getHealth(), 100);
        assertEquals(p.getMaxHealth(), 100);
        assertEquals(p.getEnergy(), 250);
        assertEquals(p.getMaxEnergy(), 250);
        assertEquals(p.getOffenseA(), 15.0, 0);
        assertEquals(p.getOffenseB(), 23.0, 0);
        assertEquals(p.getAccuracy(), 0.98, 0);
        assertEquals(p.getEvade(), 0.5, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.5, 0);
        assertEquals(p.getResistB(), 0.5, 0);
        String[] a = p.buildActions();
        assertEquals(a[0], SOFTWARE_DEV);
        assertEquals(a[1], MISS_SEMICOLON);
        assertEquals(a[2], PRESENTATION);
    }

    @Test
    public void test_get_console_glyph(){
        Researcher p = new Researcher();
        ConsoleGlyph g = p.getConsoleGlyph();
        assertNotNull(g);
    }

    @Test
    public void test_attackPC(){
        Researcher p = new Researcher();
        boolean check = false;
        String c = p.attackPC();
        if(c.equals(SOFTWARE_DEV) || c.equals(MISS_SEMICOLON) || c.equals(PRESENTATION) || c.equals(ATTACK)){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_Compare(){
        Researcher p = new Researcher();
        Freshman f  = new Freshman();
        int x = p.getInitiativeID()-f.getInitiativeID();
        assertEquals(x, p.compareTo(f));
    }

    @Test
    public void test_UseEnergy(){
        Researcher p = new Researcher();
        assertTrue(p.useEnergy(1));
        assertFalse(p.useEnergy(10000));
    }
}
