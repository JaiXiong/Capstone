package asset.character;
import console.ConsoleGlyph;
import org.junit.*;


import static engine.ActionDefinitions.*;
import static org.junit.Assert.*;

public class Test_Dean {
    @Test
    public void Test_makeDean(){
        Dean p = new Dean();
        assertEquals(p.getInitiativeID(), 7);
        assertEquals(p.getName(), "dean");
        assertEquals(p.getLeadName(), "Dean");
        assertEquals(p.getXP(), 175);
        assertEquals(p.getHealth(), 350);
        assertEquals(p.getMaxHealth(), 350);
        assertEquals(p.getEnergy(), 250);
        assertEquals(p.getMaxEnergy(), 250);
        assertEquals(p.getOffenseA(), 30.0, 0);
        assertEquals(p.getOffenseB(), 30.0, 0);
        assertEquals(p.getAccuracy(), 0.98, 0);
        assertEquals(p.getEvade(), 0.65, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.55, 0);
        assertEquals(p.getResistB(), 0.55, 0);
        String[] a = p.buildActions();
        assertEquals(a[0], ACCREDITATION);
        assertEquals(a[1], COURSE_EVAL);
        assertEquals(a[2], TECH_UPGRADE);
    }

    @Test
    public void test_get_console_glyph(){
        Dean p = new Dean();
        ConsoleGlyph g = p.getConsoleGlyph();
        assertNotNull(g);
    }

    @Test
    public void test_attackPC(){
        Dean p = new Dean();
        boolean check = false;
        String c = p.attackPC();
        if(c.equals(ACCREDITATION) || c.equals(COURSE_EVAL) || c.equals(TECH_UPGRADE) || c.equals(ATTACK)){
            check = true;
        }
        assertTrue(check);
    }


}
