package asset.items;

import asset.items.GearTable;
import org.junit.*;
import static org.junit.Assert.*;
public class Test_GearTable {
    @Test
    public void test_lookupExceptionName(){
        final String id = "matt";
        GearTable.CannotEquipException g = assertThrows(GearTable.CannotEquipException.class,() ->GearTable.lookupEquipmentByName(id));
        assertEquals(g.getMessage(), "Can't equip" + id);
        final String id2 = "other";
        g = assertThrows(GearTable.CannotEquipException.class,() ->GearTable.lookupEquipmentByName(id2));
        assertEquals(g.getMessage(), "Can't equip" + id2);
    }

    @Test
    public void test_lookupExceptionIdNum(){
        final int id = 100000000;
        GearTable.CannotEquipException g = assertThrows(GearTable.CannotEquipException.class,() ->GearTable.lookupEquipmentByID(id));
        assertEquals(g.getMessage(), "Item ID error, ID number " + id);

        final int id2 = 999999;
        g = assertThrows(GearTable.CannotEquipException.class,() ->GearTable.lookupEquipmentByID(id));
        assertEquals(g.getMessage(), "Item ID error, ID number " + id2);
    }

    @Test
    public void test_lookupByName1(){
        //Todo add more tests when table of equipable items is expanded
    }

    @Test
    public void test_lookupById1(){
        //Todo add more tests when table of equipable items is expanded
    }


}
