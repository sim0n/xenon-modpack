package dev.sim0n.modpack.binding;

import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.function.BiFunction;

/**
 * @author sim0n
 */
@RequiredArgsConstructor
public class PartitioningBindingFactory implements BindingFactory {
    private final BiFunction<Runnable, Set<Integer>, Binding> func;

    @Override
    public Binding makeBinding(Runnable action, Set<Integer> requiredKeyCodes) {
        return this.func.apply(action, requiredKeyCodes);
    }
}
