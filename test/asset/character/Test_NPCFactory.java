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
    /*
    //Fill in tests as NPCFactory is finished
    @Test
    public void test_ID2(){}

    @Test
    public void test_ID3(){}

    @Test
    public void test_ID4(){}

    @Test
    public void test_ID5(){}

    @Test
    public void test_ID6(){}

    @Test
    public void test_ID7(){}

    @Test
    public void test_ID8(){}

    @Test
    public void test_ID9(){}

    @Test
    public void test_ID11(){}

    @Test
    public void test_ID12(){}

    @Test
    public void test_ID13(){}

    @Test
    public void test_ID14(){}

     */

}
