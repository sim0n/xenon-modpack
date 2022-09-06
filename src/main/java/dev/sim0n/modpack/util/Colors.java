package dev.sim0n.modpack.util;

import lombok.experimental.UtilityClass;

import java.awt.*;

/**
 * @author sim0n
 */
@UtilityClass
public class Colors {
    public Color ACCENT = new Color(7, 90, 240);
    public Color WHITE = new Color(255, 255, 255);
    public Color BLACK = black(255);
    public Color GREY = grey(255);
    public Color BLUE = blue(255);

    public Color black(int alpha) {
        alpha = Math.min(Math.max(alpha, 0), 255);

        return new Color(10, 10, 10, alpha);
    }

    public Color grey(int alpha) {
        alpha = Math.min(Math.max(alpha, 0), 255);

        return new Color(170, 170, 170, alpha);
    }

    public Color blue(int alpha) {
        alpha = Math.min(Math.max(alpha, 0), 255);

        return new Color(90, 60, 255, alpha);
    }

    public Color accent(int alpha) {
        alpha = Math.min(Math.max(alpha, 0), 255);

        return new Color(7, 90, 240, alpha);
    }

    public Color white(int alpha) {
        alpha = Math.min(Math.max(alpha, 0), 255);

        return new Color(255, 255, 255, alpha);
    }
}
