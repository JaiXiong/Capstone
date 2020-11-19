package io.file;

import engine.Gamestate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    private static final String SAVE_DIRECTORY = "./save";
    private static final String SAVE_FILE = SAVE_DIRECTORY + "/" + "savedGame.obj";

    public static void deleteSavedGame() {
        ensureDirectoryExists();
        try {
            Files.deleteIfExists(Paths.get(SAVE_FILE));
        } catch (IOException e) {
            throw new IllegalStateException("IOException during save file delete: " + e.getMessage());
        }
    }

    public static boolean loadSavedGame() {
        ensureDirectoryExists();
        if (!Files.exists(Paths.get(SAVE_FILE))) {
            return false;
        }
        try {
            FileInputStream fis = new FileInputStream(SAVE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Gamestate.loadInstance((Gamestate)ois.readObject());
            ois.close();
            fis.close();
            return true;
        } catch (FileNotFoundException fnfe) {
            throw new IllegalStateException("Save file not found.");
        } catch (IOException ioe) {
            throw new IllegalStateException("IOException creating outputStream: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            throw new IllegalStateException("ClassNotFoundException reading from savefile: " + cnfe.getMessage());
        }
    }

    public static void saveGame() {
        ensureDirectoryExists();
        try {
            if (!Files.exists(Paths.get(SAVE_FILE)))
                Files.createFile(Paths.get(SAVE_FILE));
            FileOutputStream fos = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Gamestate.getInstance());
            oos.close();
            fos.close();
        } catch (FileNotFoundException fnfe) {
            throw new IllegalStateException("FileNotFoundException during save file creation: " + fnfe.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            //throw new IllegalStateException("IOException during save file creation: " + ioe.getMessage());
        }
    }

    public static boolean doesSavedGameExist() {
        ensureDirectoryExists();
        return Files.exists(Paths.get(SAVE_FILE));
    }

    private static void ensureDirectoryExists() {
        if (!Files.exists(Paths.get(SAVE_DIRECTORY))) {
            try {
                Files.createDirectory(Paths.get(SAVE_DIRECTORY));
            } catch (IOException e) {
                throw new IllegalStateException("Failed to create save file directory.");
            }
        }
    }
}
