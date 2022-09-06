package dev.sim0n.modpack.util.animation;

import lombok.RequiredArgsConstructor;

import java.util.function.ToDoubleFunction;

import static java.lang.Math.*;

/**
 * @author sim0n
 * @author easings.net
 */
@RequiredArgsConstructor
public enum Easing {
    LINEAR(x -> x),
    // in quad
    FAST_TO_SLOW(x -> pow(x, 2)),
    // in cubic
    SLOW_TO_FAST(x -> pow(x, 3)),
    OUT_CUBIC(x -> 1 - pow(1 - x, 3)),
    IN_OUT_QUART(x -> x < 0.5 ? 8 * x * x * x * x : 1 - pow(-2 * x + 2, 4) / 2),
    IN_OUT_QUINT(x -> x < 0.5 ? 16 * x * x * x * x * x : 1 - pow(-2 * x + 2, 5) / 2);

    private final ToDoubleFunction<Double> function;

    public double apply(double progress) {
        return this.function.applyAsDouble(progress);
    }

}
