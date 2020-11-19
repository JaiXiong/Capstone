package engine;

import java.util.*;

public class Messages {

    static Queue<String> messagesQueue = new LinkedList<>();

    //Add new message to the end of the queue.
    public static void addMessage(String message) {
        messagesQueue.add(message);
    }

    //Get the message at the head of the queue and remove it. Returns an empty string if there is no message.
    public static String getNextMessage() {
        String ret = messagesQueue.poll();
        if(ret == null) return "";
        else return ret;
    }
}
