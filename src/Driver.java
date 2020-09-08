import java.awt.*;

public class Driver {
    public static void main(String[] args) {
        Console console = new Console(24, 64, new Dimension(9, 16));
        console.update(1, 1, 'T');
        console.update(1, 2, 'e');
        console.update(1, 3, 's');
        console.update(1, 4, 't');
        console.refresh();
    }
}
