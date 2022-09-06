package dev.sim0n.modpack.binding;

import java.util.Set;

/**
 * @author sim0n
 */
public interface BindingFactory {
    /**
     * Creates a new binding
     * @param action The action to be executed when the binding is used
     * @param requiredKeyCodes The key codes required to be pressed for the binding to be used
     * @return The binding
     */
    Binding makeBinding(Runnable action, Set<Integer> requiredKeyCodes);
}
