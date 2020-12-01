package engine;

import io.gui.GUIManager;

import java.util.*;

public class Messages {

    static ArrayList<String> messageList = new ArrayList<>();

    //Add new message to the end of the queue.
    public static void addMessage(String message) {
        messageList.add(message);
        GUIManager.getInstance().updateScreen();
    }

    public static void clearMessages() {
        messageList = new ArrayList<>();
    }

    public static ArrayList<String> getMessages() {
        return messageList;
    }
}
