package dev.sim0n.modpack.event.bus;

import java.util.function.Predicate;

/**
 * @author sim0n
 */
public interface EventFilter<T> extends Predicate<T> {

    @Override
    boolean test(T t);
}
