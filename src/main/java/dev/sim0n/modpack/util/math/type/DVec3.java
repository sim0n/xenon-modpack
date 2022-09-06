package dev.sim0n.modpack.util.math.type;

/**
 * @author sim0n
 */
public class DVec3 extends Vec3<Double> {
    public DVec3(double x, double y, double z) {
        super(x, y, z);
    }

    @Override
    public Vec3<Double> add(Double value) {
        this.x += value;
        this.y += value;
        this.z += value;

        return this;
    }

    @Override
    public Vec3<Double> subtract(Double value) {
        this.x -= value;
        this.y -= value;
        this.z -= value;

        return this;
    }

    @Override
    public Vec3<Double> divide(Double value) {
        this.x /= value;
        this.y /= value;
        this.z /= value;

        return this;
    }

    @Override
    public Vec3<Double> multiply(Double value) {
        this.x *= value;
        this.y *= value;
        this.z *= value;

        return this;
    }
}
