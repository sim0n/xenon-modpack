package dev.sim0n.modpack.binding;

import dev.sim0n.modpack.event.impl.game.key.KeyState;

import java.util.Queue;
import java.util.Set;

/**
 * @author sim0n
 */
public interface BindingSystem {

    /**
     * Creates a bind for the required key codes with the given action.
     * @param action The action to be executed when the bind is activated.
     * @param requiredKeyCodes The required key codes for the bind to be activated.
     * @return The created bind.
     */
    Binding makeBinding(Runnable action, Set<Integer> requiredKeyCodes);


    boolean unbind(Binding binding);

    /**
     * @return A collection of all the bindings.
     */
    Queue<Binding> getBindings();

    /**
     * @param key The key code to check
     * @return If the key is currently bound
     */
    boolean isBound(int key);

    /**
     * Invokes a key event to the binding system.
     * @param state The state of the key.
     * @param code The key code.
     */
    void invokeKey(KeyState state, int code);
}
