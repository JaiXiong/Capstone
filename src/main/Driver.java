package main;

import io.gui.GUIManager;

public class Driver {

    private static int devBoost;

    public static void main(String[] args) {
        devBoost = args.length > 0 ? Integer.parseInt(args[0]) : 0;
        GUIManager.getInstance(); //start the GUI
    }

    public static int getDevBoost() {
        return devBoost;
    }

}
