package asset.items;
import asset.items.Item;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_Item {
    @Test
    public void test_getId(){
        Item i = new Item(0, "Energy Drink", 15, 0 , 0);
        assertEquals(i.getItemID(), 0);
    }

    @Test
    public void test_getName(){
        Item i = new Item(0, "Energy Drink", 15, 0, 0);
        assertEquals(i.getName(), "Energy Drink");
    }

    @Test
    public void test_getPrice(){
        Item i = new Item(0, "Energy Drink", 15, 0, 0);
        assertEquals(i.getPricetag(), 15);
    }

    @Test
    public void test_CreateItem(){
        Item I = new Item(100, "Energy Drink", 4, 0, 0);
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
        Item I = new Item(101, "Big Energy Drink", 6, 0, 0);
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

    @Test
    public void test_CreateItem3(){
        Item I = new Item(102, "'Juice' (21+)", 6, 0, 0);
        Item j = Item.createItem(102);
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
    public void test_CreateItem4(){
        Item I = new Item(103, "Craft Dinner", 6, 0, 0);
        Item j = Item.createItem(103);
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
    public void test_CreateItem5(){
        Item I = new Item(104, "Some Kinda Tex-Mex?", 10, 0, 0);
        Item j = Item.createItem(104);
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
