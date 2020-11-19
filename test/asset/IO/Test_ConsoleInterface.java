package asset.IO;
import io.gui.ConsoleInterface;
import org.junit.Test;


import java.awt.event.KeyListener;


import static org.junit.Assert.*;

public class Test_ConsoleInterface {
    @Test
    public void test_getVertCenter(){
        KeyListener k = null;
        ConsoleInterface c = new ConsoleInterface(k);
        assertEquals(c.getVerticalCenter(10), 11);
    }

    @Test
    public void test_getVertCenterInv(){
        KeyListener k = null;
        ConsoleInterface c = new ConsoleInterface(k);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            c.getVerticalCenter(40);
        });

        String expectedMessage = "Input text too large to center on the Console.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}
