package asset.Engine;

import engine.Messages;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class Test_Messages {
    @Test
    public void test_getMessages(){
        Messages.addMessage("This is a test message");
        Messages.addMessage("Morgan's test sentence");
        Messages.addMessage("Matt's test message");
        ArrayList<String> a = Messages.getMessages();
        assertEquals(a.get(0), "This is a test message");
        assertEquals(a.get(1), "Morgan's test sentence");
        assertEquals(a.get(2), "Matt's test message");

    }
}
