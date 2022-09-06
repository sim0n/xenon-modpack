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
public abstract class Vec2<T extends Number> {
    protected T x;
    protected T y;

    public abstract Vec2<T> add(T value);
    public abstract Vec2<T> subtract(T value);
    public abstract Vec2<T> divide(T value);
    public abstract Vec2<T> multiply(T value);
}
