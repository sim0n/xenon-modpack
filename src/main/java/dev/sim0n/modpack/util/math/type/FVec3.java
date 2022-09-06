package dev.sim0n.modpack.util.math.type;

/**
 * @author sim0n
 */
public class FVec3 extends Vec3<Float> {
    public FVec3(float x, float y, float z) {
        super(x, y, z);
    }

    @Override
    public Vec3<Float> add(Float value) {
        this.x += value;
        this.y += value;
        this.z += value;

        return this;
    }

    @Override
    public Vec3<Float> subtract(Float value) {
        this.x -= value;
        this.y -= value;
        this.z -= value;

        return this;
    }

    @Override
    public Vec3<Float> divide(Float value) {
        this.x /= value;
        this.y /= value;
        this.z /= value;

        return this;
    }

    @Override
    public Vec3<Float> multiply(Float value) {
        this.x *= value;
        this.y *= value;
        this.z *= value;

        return this;
    }
}
