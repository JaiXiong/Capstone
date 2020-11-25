package io;

import java.awt.*;

public class FormatUtility {
    public static Color colorizeByPercentage(double pct) {
        if (pct > 1.0) return Color.MAGENTA;
        if (pct > 0.9) return Color.BLUE;
        if (pct > 0.66) return Color.GREEN;
        if (pct > 0.33) return Color.YELLOW;
        if (pct > 0.1) return Color.ORANGE;
        return Color.RED;
    }

    public static String percentage(double pct) {
        return (int)(100 * pct) + "%";
    }
}
