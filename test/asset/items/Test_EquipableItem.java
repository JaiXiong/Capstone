package asset.items;

import asset.items.EquipableItem;
import org.junit.*;
import static org.junit.Assert.*;
public class Test_EquipableItem {
    @Test
    public void testSlots(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2, 5, 5.0, 5.0, 9.0, .5, 5.0 ,1.0, 1.0, 1.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2, 1, 1.0, 1.0, 4.0, .9, 6.0 ,3.0, 1.0, 1.0);

        assertEquals(e1.getSlot(),EquipableItem.Slot.OFFENSE_A );
        assertEquals(e2.getSlot(),EquipableItem.Slot.OFFENSE_B);
        assertEquals(e3.getSlot(),EquipableItem.Slot.DEFENSE_A );
        assertEquals(e4.getSlot(),EquipableItem.Slot.DEFENSE_B );
    }

    @Test
    public void testNames(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2, 5, 5.0, 5.0, 9.0, .5, 5.0 ,1.0, 1.0, 1.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2, 1, 1.0, 1.0, 4.0, .9, 6.0 ,3.0, 1.0, 1.0);

        assertEquals(e1.getName(), "Stick");
        assertEquals(e2.getName(), "Sword");
        assertEquals(e3.getName(), "Jacket");
        assertEquals(e4.getName(), "Armor");

    }

    @Test
    public void testId(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2, 5, 5.0, 5.0, 9.0, .5, 5.0 ,1.0, 1.0, 1.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2, 1, 1.0, 1.0, 4.0, .9, 6.0 ,3.0, 1.0, 1.0);

        assertEquals(e1.getItemID(), 0);
        assertEquals(e2.getItemID(), 1);
        assertEquals(e3.getItemID(), 2);
        assertEquals(e4.getItemID(), 3);

    }

    @Test
    public void testPrice(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2, 5, 5.0, 5.0, 9.0, .5, 5.0 ,1.0, 1.0, 1.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2, 1, 1.0, 1.0, 4.0, .9, 6.0 ,3.0, 1.0, 1.0);

        assertEquals(e1.getPricetag(), 5);
        assertEquals(e2.getPricetag(), 10);
        assertEquals(e3.getPricetag(), 1);
        assertEquals(e4.getPricetag(), 5);
    }

    @Test
    public void testMoveRate(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2, 5, 5.0, 5.0, 9.0, .5, 5.0 ,1.0, 1.0, 1.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2, 1, 1.0, 1.0, 4.0, .9, 6.0 ,3.0, 1.0, 1.0);

        assertEquals(e1.getMoveRate(), 3);
        assertEquals(e2.getMoveRate(), 2);
        assertEquals(e3.getMoveRate(), 3);
        assertEquals(e4.getMoveRate(), 2);
    }

    @Test
    public void testOffense(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2, 5, 5.0, 5.0, 9.0, .5, 5.0 ,1.0, 1.0, 1.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2, 1, 1.0, 1.0, 4.0, .9, 6.0 ,3.0, 1.0, 1.0);

        assertEquals(e1.getOffenseA(), 1.0, 0);
        assertEquals(e2.getOffenseA(), 5.0, 0);
        assertEquals(e3.getOffenseA(), 1.0, 0);
        assertEquals(e4.getOffenseA(), 1.0, 0);

        assertEquals(e1.getOffenseB(), 2.0, 0);
        assertEquals(e2.getOffenseB(), 5.0, 0);
        assertEquals(e3.getOffenseB(), 2.0, 0);
        assertEquals(e4.getOffenseB(), 1.0, 0);
    }

    @Test
    public void testAccuracy(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2, 5, 5.0, 5.0, 9.0, .5, 5.0 ,1.0, 1.0, 1.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2, 1, 1.0, 1.0, 4.0, .9, 6.0 ,3.0, 1.0, 1.0);

        assertEquals(e1.getAccuracy(), 9.0, 0);
        assertEquals(e2.getAccuracy(), 9.0, 0);
        assertEquals(e3.getAccuracy(), 9.0, 0);
        assertEquals(e4.getAccuracy(), 4.0, 0);
    }

    @Test
    public void testEvade(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2, 5, 5.0, 5.0, 9.0, .5, 5.0 ,1.0, 1.0, 1.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2, 1, 1.0, 1.0, 4.0, .9, 6.0 ,3.0, 1.0, 1.0);

        assertEquals(e1.getEvade(), 1.0, 0);
        assertEquals(e2.getEvade(), .5, 0);
        assertEquals(e3.getEvade(), 1.0, 0);
        assertEquals(e4.getEvade(), .9, 0);
    }

    @Test
    public void testDefense(){
        EquipableItem e1 = new EquipableItem(0, EquipableItem.Slot.OFFENSE_A, "Stick", 5, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e2 = new EquipableItem(1, EquipableItem.Slot.OFFENSE_B, "Sword", 10, 2, 5, 5.0, 5.0, 9.0, .5, 5.0 ,1.0, 1.0, 1.0);
        EquipableItem e3 = new EquipableItem(2, EquipableItem.Slot.DEFENSE_A, "Jacket", 1, 3, 1, 1.0, 2.0, 9.0, 1.0, 1.0 ,1.0, 1.0, 1.0);
        EquipableItem e4 = new EquipableItem(3, EquipableItem.Slot.DEFENSE_B, "Armor", 5, 2, 1, 1.0, 1.0, 4.0, .9, 6.0 ,3.0, 1.0, 1.0);

        assertEquals(e1.getDefenseA(), 1.0, 0);
        assertEquals(e2.getDefenseA(), 5.0, 0);
        assertEquals(e3.getDefenseA(), 1.0, 0);
        assertEquals(e4.getDefenseA(), 6.0, 0);

        assertEquals(e1.getDefenseB(), 1.0, 0);
        assertEquals(e2.getDefenseB(), 1.0, 0);
        assertEquals(e3.getDefenseB(), 1.0, 0);
        assertEquals(e4.getDefenseB(), 3.0, 0);

        assertEquals(e1.getDefenseC(), 1.0, 0);
        assertEquals(e2.getDefenseC(), 1.0, 0);
        assertEquals(e3.getDefenseC(), 1.0, 0);
        assertEquals(e4.getDefenseC(), 1.0, 0);

        assertEquals(e1.getDefenseD(), 1.0, 0);
        assertEquals(e2.getDefenseD(), 1.0, 0);
        assertEquals(e3.getDefenseD(), 1.0, 0);
        assertEquals(e4.getDefenseD(), 1.0, 0);
    }
}
