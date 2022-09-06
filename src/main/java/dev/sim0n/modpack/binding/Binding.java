package dev.sim0n.modpack.binding;

import dev.sim0n.modpack.binding.listener.KeyListener;

import java.util.Set;

/**
 * @author sim0n
 */
public interface Binding extends KeyListener {
    /**
     * @return The action to be executed when the binding is used
     */
    Runnable getAction();

    /**
     * @return The key codes required to be pressed for the binding to be used
     */
    Set<Integer> getRequiredKeyCodes();

    /**
     * Sets up the required key codes for the binding
     * @param requiredKeyCodes the required key codes
     */
    void bind(int... requiredKeyCodes);

    /**
     * @return Whether the binding can be used
     */
    boolean canUseAction();
}
