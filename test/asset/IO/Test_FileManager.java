package asset.IO;
import io.file.FileManager;
import org.junit.Test;


import static org.junit.Assert.*;

public class Test_FileManager {
    /*
    @Test
    public void test_DeleteSave(){
        Exception exception = assertThrows(IllegalStateException.class, FileManager::deleteSavedGame);

        String expectedMessage = "IOException during save file delete: ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

     */

    @Test
    public void test_Loadsave(){
        assertFalse(FileManager.loadSavedGame());
    }

    @Test
    public void test_DoesSavedGameExist(){
        assertFalse(FileManager.doesSavedGameExist());
    }
}
