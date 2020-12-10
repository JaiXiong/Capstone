package asset.character;

import console.ConsoleGlyph;
import org.junit.*;


import static engine.ActionDefinitions.*;
import static org.junit.Assert.*;
public class Test_Professor {
    @Test
    public void Test_MakeProfessor(){
        Professor p = new Professor();
        assertEquals(p.getInitiativeID(), 6);
        assertEquals(p.getName(), "professor");
        assertEquals(p.getLeadName(), "Professor");
        assertEquals(p.getXP(), 175);
        assertEquals(p.getHealth(), 300);
        assertEquals(p.getMaxHealth(), 300);
        assertEquals(p.getEnergy(), 225);
        assertEquals(p.getMaxEnergy(), 225);
        assertEquals(p.getOffenseA(), 25.0, 0);
        assertEquals(p.getOffenseB(), 25.0, 0);
        assertEquals(p.getAccuracy(), 0.97, 0);
        assertEquals(p.getEvade(), 0.6, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.6, 0);
        assertEquals(p.getResistB(), 0.6, 0);
        String[] a = p.buildActions();
        assertEquals(a[0], LECTURE);
        assertEquals(a[1], ASSIGN_HOMEWORK);
        assertEquals(a[2], LIVE_CODE);
    }

    @Test
    public void test_get_console_glyph(){
        Professor p = new Professor();
        ConsoleGlyph g = p.getConsoleGlyph();
        assertNotNull(g);
    }

    @Test
    public void test_attackPC(){
        Professor p = new Professor();
        boolean check = false;
        String c = p.attackPC();
        if(c.equals(LIVE_CODE) || c.equals(ASSIGN_HOMEWORK) || c.equals(LECTURE) || c.equals(ATTACK)){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_Compare(){
        Professor p = new Professor();
        Freshman f  = new Freshman();
        int x = p.getInitiativeID()-f.getInitiativeID();
        assertEquals(x, p.compareTo(f));
    }
}
