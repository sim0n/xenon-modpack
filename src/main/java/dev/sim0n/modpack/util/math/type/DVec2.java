package dev.sim0n.modpack.util.math.type;

/**
 * @author sim0n
 */
public class DVec2 extends Vec2<Double> {
    public DVec2(double x, double y) {
        super(x, y);
    }

    @Override
    public Vec2<Double> add(Double value) {
        this.x += value;
        this.y += value;

        return this;
    }

    @Override
    public Vec2<Double> subtract(Double value) {
        this.x -= value;
        this.y -= value;

        return this;
    }

    @Override
    public Vec2<Double> divide(Double value) {
        this.x /= value;
        this.y /= value;

        return this;
    }

    @Override
    public Vec2<Double> multiply(Double value) {
        this.x *= value;
        this.y *= value;

        return this;
    }
}
