package asset.character;

import console.ConsoleGlyph;
import org.junit.*;

import static engine.ActionDefinitions.*;
import static org.junit.Assert.*;
public class Test_Senior {
    @Test
    public void Test_MakeSenior(){
        Senior p = new Senior();
        assertEquals(p.getInitiativeID(), 4);
        assertEquals(p.getName(), "senior");
        assertEquals(p.getLeadName(), "Senior");
        assertEquals(p.getXP(), 150);
        assertEquals(p.getHealth(), 150);
        assertEquals(p.getMaxHealth(), 150);
        assertEquals(p.getEnergy(), 150);
        assertEquals(p.getMaxEnergy(), 150);
        assertEquals(p.getOffenseA(), 20.0, 0);
        assertEquals(p.getOffenseB(), 20.0, 0);
        assertEquals(p.getAccuracy(), 0.9, 0);
        assertEquals(p.getEvade(), 0.7, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.7, 0);
        assertEquals(p.getResistB(), 0.7, 0);
        String[] a = p.buildActions();
        assertEquals(a[0], FINAL_PROJECT);
        assertEquals(a[1], CODE_REVIEW);
    }

    @Test
    public void test_get_console_glyph(){
        Senior p = new Senior();
        ConsoleGlyph g = p.getConsoleGlyph();
        assertNotNull(g);
    }

    @Test
    public void test_attackPC(){
        Senior p = new Senior();
        boolean check = false;
        String c = p.attackPC();
        if(c.equals(CODE_REVIEW) || c.equals(FINAL_PROJECT) || c.equals(ATTACK)){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_Compare(){
        Senior p = new Senior();
        Freshman f  = new Freshman();
        int x = p.getInitiativeID()-f.getInitiativeID();
        assertEquals(x, p.compareTo(f));
    }

    @Test
    public void test_UseEnergy(){
        Senior p = new Senior();
        assertTrue(p.useEnergy(1));
        assertFalse(p.useEnergy(10000));
    }

}
