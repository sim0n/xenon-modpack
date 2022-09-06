package dev.sim0n.modpack.util.helper;

import lombok.experimental.UtilityClass;

/**
 * @author sim0n
 */
@UtilityClass
public class MathHelper {

    public int interpolate(int last, int now, float factor) {
        return (int) (last + (now - last) * factor);
    }

    public double interpolate(double now, double destination, double factor) {
        return now + (destination - now) * factor;
    }

    public float interpolate(float now, float destination, float factor) {
        return now + (destination - now) * factor;
    }
}
