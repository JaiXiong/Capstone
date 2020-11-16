package engine;

import java.util.Random;

public class RNG {
    private static final Random RNG = new Random();

    public static Random get() {
        return RNG;
    }
}
