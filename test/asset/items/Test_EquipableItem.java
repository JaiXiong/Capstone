package asset.items;

import asset.items.EquipableItem;
import org.junit.*;
import static org.junit.Assert.*;
public class Test_EquipableItem {
    @Test
    public void testSlots(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3.0,  1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2.0,  5.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3.0,  1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2.5,  1.0);

        assertEquals(e1.getSlot(),EquipableItem.Slot.OFFENSE_A );
        assertEquals(e2.getSlot(),EquipableItem.Slot.OFFENSE_B);
        assertEquals(e3.getSlot(),EquipableItem.Slot.DEFENSE_A );
        assertEquals(e4.getSlot(),EquipableItem.Slot.DEFENSE_B );
    }

    @Test
    public void testNames(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3.0,  1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2.0,  5.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3.0,  1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2.5,  1.0);
        assertEquals(e1.getName(), "Stick");
        assertEquals(e2.getName(), "Sword");
        assertEquals(e3.getName(), "Jacket");
        assertEquals(e4.getName(), "Armor");

    }

    @Test
    public void testId(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3.0,  1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2.0,  5.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3.0,  1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2.5,  1.0);
        assertEquals(e1.getItemID(), 0);
        assertEquals(e2.getItemID(), 1);
        assertEquals(e3.getItemID(), 2);
        assertEquals(e4.getItemID(), 3);

    }

    @Test
    public void testPrice(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3.0,  1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2.0,  5.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3.0,  1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2.5,  1.0);
        assertEquals(e1.getPricetag(), 5);
        assertEquals(e2.getPricetag(), 10);
        assertEquals(e3.getPricetag(), 1);
        assertEquals(e4.getPricetag(), 5);
    }

    @Test
    public void testBaseStat(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3.0,  1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2.0,  5.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3.0,  1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2.5,  1.0);
        assertEquals(e1.getBaseStat(), 3.0, 0);
        assertEquals(e2.getBaseStat(), 2.0, 0);
        assertEquals(e3.getBaseStat(), 3.0, 0);
        assertEquals(e4.getBaseStat(), 2.5, 0);
    }

    @Test
    public void testHitModifier(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3.0,  1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2.0,  5.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3.0,  1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2.5,  1.0);
        assertEquals(e1.getToHitModifier(), 1.0, 0);
        assertEquals(e2.getToHitModifier(), 5.0, 0);
        assertEquals(e3.getToHitModifier(), 1.0, 0);
        assertEquals(e4.getToHitModifier(), 1.0, 0);
    }

    @Test
    public void test_CreateEquipment(){
        EquipableItem q;
        q = EquipableItem.createEquipment(0);
        assertEquals(0, q.getItemID());
        q = EquipableItem.createEquipment(1);
        assertEquals(1, q.getItemID());
        q = EquipableItem.createEquipment(2);
        assertEquals(2, q.getItemID());
        q = EquipableItem.createEquipment(3);
        assertEquals(3, q.getItemID());
        q = EquipableItem.createEquipment(4);
        assertEquals(4, q.getItemID());
        q = EquipableItem.createEquipment(5);
        assertEquals(5, q.getItemID());
        q = EquipableItem.createEquipment(6);
        assertEquals(6, q.getItemID());
        q = EquipableItem.createEquipment(7);
        assertEquals(7, q.getItemID());
        q = EquipableItem.createEquipment(8);
        assertEquals(8, q.getItemID());
        q = EquipableItem.createEquipment(9);
        assertEquals(9, q.getItemID());
        q = EquipableItem.createEquipment(10);
        assertEquals(10, q.getItemID());
        q = EquipableItem.createEquipment(11);
        assertEquals(11, q.getItemID());
        q = EquipableItem.createEquipment(12);
        assertEquals(12, q.getItemID());
        q = EquipableItem.createEquipment(13);
        assertEquals(13, q.getItemID());
        q = EquipableItem.createEquipment(14);
        assertEquals(14, q.getItemID());
        q = EquipableItem.createEquipment(15);
        assertEquals(15, q.getItemID());
        q = EquipableItem.createEquipment(16);
        assertEquals(16, q.getItemID());
        q = EquipableItem.createEquipment(17);
        assertEquals(17, q.getItemID());
        q = EquipableItem.createEquipment(18);
        assertEquals(18, q.getItemID());
        q = EquipableItem.createEquipment(19);
        assertEquals(19, q.getItemID());
        q = EquipableItem.createEquipment(20);
        assertEquals(20, q.getItemID());
        q = EquipableItem.createEquipment(21);
        assertEquals(21, q.getItemID());
        q = EquipableItem.createEquipment(22);
        assertEquals(22, q.getItemID());
        q = EquipableItem.createEquipment(23);
        assertNull(q);

    }


}
