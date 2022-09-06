package dev.sim0n.modpack.util.math.type;

/**
 * @author sim0n
 */
public class FVec2 extends Vec2<Float> {
    public FVec2(float x, float y) {
        super(x, y);
    }

    @Override
    public Vec2<Float> add(Float value) {
        this.x += value;
        this.y += value;

        return this;
    }

    @Override
    public Vec2<Float> subtract(Float value) {
        this.x -= value;
        this.y -= value;

        return this;
    }

    @Override
    public Vec2<Float> divide(Float value) {
        this.x /= value;
        this.y /= value;

        return this;
    }

    @Override
    public Vec2<Float> multiply(Float value) {
        this.x *= value;
        this.y *= value;

        return this;
    }
}
