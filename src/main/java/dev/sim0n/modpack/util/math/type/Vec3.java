package dev.sim0n.modpack.util.math.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author sim0n
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Vec3<T extends Number> {
    protected T x;
    protected T y;
    protected T z;

    public abstract Vec3<T> add(T value);
    public abstract Vec3<T> subtract(T value);
    public abstract Vec3<T> divide(T value);
    public abstract Vec3<T> multiply(T value);
}