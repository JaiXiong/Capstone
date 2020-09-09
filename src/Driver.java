import java.awt.*;

public class Driver {

    private static final Console CONSOLE =
            new Console(24, 64, new Dimension(9, 16));

    public static void main(String[] args) {
        printString(1, 1, "Test");
    }

    public static void printString(int row, int initialColumn, String displayString) {
        int currentColumn;
        for (int i = 0; i < displayString.length(); ++i) {
            currentColumn = initialColumn + i;
            if (CONSOLE.validatePosition(row, currentColumn))
                CONSOLE.update(row, currentColumn, displayString.charAt(i));
            else break;
        }
        CONSOLE.refresh();
    }
}
