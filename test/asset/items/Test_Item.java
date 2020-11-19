package asset.items;
import asset.items.Item;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Item {
    @Test
    public void test_getId(){
        Item i = new Item(0, "Energy Drink", 15);
        assertEquals(i.getItemID(), 0);
    }

    @Test
    public void test_getName(){
        Item i = new Item(0, "Energy Drink", 15);
        assertEquals(i.getName(), "Energy Drink");
    }

    @Test
    public void test_getPrice(){
        Item i = new Item(0, "Energy Drink", 15);
        assertEquals(i.getPricetag(), 15);
    }

    @Test
    public void test_CreateItem(){
        Item I = new Item(100, "Energy Drink", 15);
        Item j = Item.createItem(100);
        boolean check = true;
        if(I.getItemID()!=j.getItemID()){
            check = false;
        }
        if(!I.getName().equals(j.getName())){
            check = false;
        }
        if(I.getPricetag() != j.getPricetag()){
            check = false;
        }
        assertTrue(check);
    }

    @Test
    public void test_CreateItem2(){
        Item I = new Item(101, "Shoddy Notes", 4);
        Item j = Item.createItem(101);
        boolean check = true;
        if(I.getItemID()!=j.getItemID()){
            check = false;
        }
        if(!I.getName().equals(j.getName())){
            check = false;
        }
        if(I.getPricetag() != j.getPricetag()){
            check = false;
        }
        assertTrue(check);
    }
}
