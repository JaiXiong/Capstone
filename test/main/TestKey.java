package main;

import main.items.Key;
import org.junit.*;
import static org.junit.Assert.*;
public class TestKey {
    @Test
    public void testKeyId(){
        Key k = new Key(0, "Key1", 1, 1111);
        Key k2 = new Key(1, "Key2", 1, 11);
        Key k3 = new Key(2, "Key3", 3, 111111);
        assertEquals(k.getItemID(), 0);
        assertEquals(k2.getItemID(), 1);
        assertEquals(k3.getItemID(), 2);

        assertEquals(k.getName(), "Key1");
        assertEquals(k.getPricetag(), 1);
        assertEquals(k.getUnlockCode(), 1111);

    }

    @Test
    public void testKeyName(){
        Key k = new Key(0, "Key1", 1, 1111);
        Key k2 = new Key(1, "Key2", 1, 11);
        Key k3 = new Key(2, "Key3", 3, 111111);

        assertEquals(k.getName(), "Key1");
        assertEquals(k2.getName(), "Key2");
        assertEquals(k3.getName(), "Key3");
    }

    @Test
    public void testKeyPrice(){
        Key k = new Key(0, "Key1", 1, 1111);
        Key k2 = new Key(1, "Key2", 2, 11);
        Key k3 = new Key(2, "Key3", 3, 111111);

        assertEquals(k.getPricetag(), 1);
        assertEquals(k2.getPricetag(), 2);
        assertEquals(k3.getPricetag(), 3);
    }

    @Test
    public void testUnlockCode(){
        Key k = new Key(0, "Key1", 1, 1111);
        Key k2 = new Key(1, "Key2", 2, 11);
        Key k3 = new Key(2, "Key3", 3, 12345);

        assertEquals(k.getUnlockCode(), 1111);
        assertEquals(k2.getUnlockCode(), 11);
        assertEquals(k3.getUnlockCode(), 12345);
    }
}
