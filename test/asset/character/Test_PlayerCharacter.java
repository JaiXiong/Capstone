package asset.character;

import asset.character.PlayerCharacter;
import asset.items.Item;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Test_PlayerCharacter {
    @Test
    public void testInit(){
        PlayerCharacter p = new PlayerCharacter();
        ArrayList<Item> i = p.getInventory();
        String[] a = p.getActions();
        assertEquals(p.getInitiativeID(), 0);
        assertEquals(p.getHealth(), 100);
        assertEquals(p.getMaxHealth(), 100);
        assertEquals(p.getEnergy(), 100);
        assertEquals(p.getMaxEnergy(), 100);
        assertEquals(p.getOffenseA(), 10.0, 0);
        assertEquals(p.getOffenseB(), 1.0, 0);
        assertEquals(p.getAccuracy(), 0.5, 0);
        assertEquals(p.getEvade(), 0.45, 0);  //presuming equip "Jacket" lowers .05
        assertEquals(p.getResistA(), 0.95, 0);
        assertEquals(p.getResistB(), 0.95, 0);
        assertEquals(p.getXP(), 0);
        assertEquals(p.getLevel(), 1);
        assertEquals(i.size(), 2);
        assertEquals(a.length, 1); //TODO no actual actions defined yet
        assertEquals(p.getEquipOffA().getName(), "Stick");
        assertNull(p.getEquipOffB());
        assertEquals(p.getEquipDefA().getName(), "Jacket");
        assertNull(p.getEquipDefB());
    }


    @Test
    public void test_xp(){
        PlayerCharacter p = new PlayerCharacter();
        //TODO add assertions for level up stat adjustments

        //make sure we can gain less XP than needed
        //to level
        p.gainXP(50);
        assertEquals(p.getXP(), 50);
        assertEquals(p.getLevel(), 1);

        //make sure we can gain exactly as much
        //XP still needed for next level
        p.gainXP(50);
        assertEquals(p.getXP(), 0);
        assertEquals(p.getLevel(), 2);

        //make sure we can gain exactly enough XP
        //for a level
        p.gainXP(200);
        assertEquals(p.getXP(), 0);
        assertEquals(p.getLevel(), 3);

        //make sure we can gain more XP than we
        //need for the next level
        p.gainXP(500);
        assertEquals(p.getXP(), 200);
        assertEquals(p.getLevel(), 4);

        //make sure we can level multiple times on a
        //large XP gain
        p.gainXP(1000);
        assertEquals(p.getXP(), 300);
        assertEquals(p.getLevel(), 6);

        //make sure we can hit level cap
        p.gainXP(19200);
        assertEquals(p.getXP(), 2000);
        assertEquals(p.getLevel(), 20);

        //normal progression would expect xp==100, level==21
        //but we hit cap so we stop levelling
        p.gainXP(2200);
        assertEquals(p.getXP(), 4200);
        assertEquals(p.getLevel(), 20);
    }

    @Test
    public void test_equip(){
        //TODO probably should wait until Item/Equipment are further along
    }

    @Test
    // takeDamage() inherited from AbstractCharacter
    /* because there's some amount of RNG in whether a source of damage
     * connects, there are limits to how completely takeDamage can be
     * unit tested
     */
    public void test_takeDamage(){
        PlayerCharacter p = new PlayerCharacter();

        //if evade is nonzero, tests will randomly fail as the PC dodges
        p.setEvade(0.0);

        //check nonfatal typeA damage
        // 20*resistA(.9) = 18 damage
        p.takeDamage(20, "typeA", 1.0);
        assertEquals(p.getHealth(), 82);

        //check nonfatal typeB damage
        // 10*resistB(.95) = 9.5 round up to 10 damage
        p.takeDamage(10, "typeB", 1.0);
        assertEquals(p.getHealth(), 72);

        //check nonfatal typeC damage
        // 15*resistC(.95) = 14.25 round down to 14 damage
        p.takeDamage(15, "typeC", 1.0);
        assertEquals(p.getHealth(), 58);

        //check nonfatal typeD damage
        // 25*resistD(.95) = 22.75 round up to 23 damage
        p.takeDamage(25, "typeD", 1.0);
        assertEquals(p.getHealth(), 35);

        //check healing that doesn't hit max
        p.takeDamage(40, "heal", 1.0);
        assertEquals(p.getHealth(), 75);

        //check overhealing
        // overhealing 25 damage
        p.takeDamage(50, "heal", 1.0);
        assertEquals(p.getHealth(), 100);

        //check overkill
        // 200*resistD(.95) = 190 damage, 90 damage overkill
        p.takeDamage(200, "typeD", 1.0);
        assertEquals(p.getHealth(), 0);
    }

    @Test
    public void test_remove(){
        PlayerCharacter p = new PlayerCharacter();
        Item i = Item.createItem(101);
        p.addToInventory(i);
        assertTrue(p.removeFromInventory(i));
        i = Item.createItem(102);
        assertFalse(p.removeFromInventory(i));
    }

    @Test
    public void test_keyRing(){
        PlayerCharacter p = new PlayerCharacter();
        Item i = Item.createItem(-1);
        p.addToInventory(i);
        assertTrue(p.keyRing(-1));
        p.removeFromInventory(i);
        assertFalse(p.keyRing(10000));
    }

    @Test
    public void test_consoleGlyph(){
        PlayerCharacter p = new PlayerCharacter();
        assertNotNull(p.getConsoleGlyph());
    }
}

