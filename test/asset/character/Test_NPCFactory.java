package asset.character;

import org.junit.*;
import static org.junit.Assert.*;
public class Test_NPCFactory {
    @Test
    public void test_ID1(){
        boolean check = false;
        if(NPCFactory.npcLookup(1) instanceof Freshman){
            check = true;
        }
        assertTrue(check);
    }
    @Test
    public void test_ID2(){
        boolean check = false;
        if(NPCFactory.npcLookup(2) instanceof Sophomore){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_ID3(){
        boolean check = false;
        if(NPCFactory.npcLookup(3) instanceof Junior){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_ID4(){
        boolean check = false;
        if(NPCFactory.npcLookup(4) instanceof Senior){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_ID5(){
        boolean check = false;
        if(NPCFactory.npcLookup(5) instanceof Hacker){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_ID6(){
        boolean check = false;
        if(NPCFactory.npcLookup(6) instanceof TA){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_ID7(){
        boolean check = false;
        if(NPCFactory.npcLookup(7) instanceof Researcher){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_ID8(){
        boolean check = false;
        if(NPCFactory.npcLookup(11) instanceof Professor){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_ID9(){
        boolean check = false;
        if(NPCFactory.npcLookup(12) instanceof Dean){
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void test_IDInvalid(){

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            NPCFactory.npcLookup(10);
        });

        String expectedMessage = "Unhandled ID: 10";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}
