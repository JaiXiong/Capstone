import main.character.PlayerCharacter;
import main.items.EquipableItem;
import main.items.Item;
import org.junit.*;
import static org.junit.Assert.*;
import java.awt.*;

public class InitialTests {
    @Test
    public void testInit(){
        PlayerCharacter p = new PlayerCharacter();
        assertEquals(p.getInitiativeID(), 0);
        assertEquals(p.getHealth(), 100);
        assertEquals(p.getEnergy(), 100);
        assertEquals(p.getMoveRate(), 1);
        assertEquals(p.getOffenseA(), 1.0, 0);
        assertEquals(p.getOffenseB(), 1.0, 0);
        assertEquals(p.getAccuracy(), 1.0, 0);
        assertEquals(p.getEvade(), 1.0, 0);
        assertEquals(p.getResistA(), 1.0, 0);
        assertEquals(p.getResistB(), 1.0, 0);
        assertEquals(p.getResistC(), 1.0, 0);
        assertEquals(p.getResistD(), 1.0, 0);
    }

    @Test
    public void test1(){
        EquipableItem e1 = new EquipableItem(EquipableItem.Slot.OFFENSE_A, "Stick");
        EquipableItem e2 = new EquipableItem(EquipableItem.Slot.OFFENSE_B, "Sword");
        EquipableItem e3 = new EquipableItem(EquipableItem.Slot.DEFENSE_A, "Armor");
        EquipableItem e4 = new EquipableItem(EquipableItem.Slot.DEFENSE_B, "Helmet");

        assertEquals(e1.getSlot(),EquipableItem.Slot.OFFENSE_A );
        assertEquals(e2.getSlot(),EquipableItem.Slot.OFFENSE_B);
        assertEquals(e3.getSlot(),EquipableItem.Slot.DEFENSE_A );
        assertEquals(e4.getSlot(),EquipableItem.Slot.DEFENSE_B );
    }

    @Test
    public void test2(){

    }
}
