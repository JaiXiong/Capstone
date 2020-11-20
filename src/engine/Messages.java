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

    /**
     * Commented out for now - I am not sure what the intent of this method was, so I don't want to delete it entirely.
     */
//    //Get the message at the head of the queue and remove it. Returns an empty string if there is no message.
//    public static String getNextMessage() {
//        String ret = messagesQueue.poll();
//        if(ret == null) return "";
//        else return ret;
//    }

    public static ArrayList<String> getMessages() {
        return messageList;
    }
}
