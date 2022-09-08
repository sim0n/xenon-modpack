package dev.sim0n.modpack.binding;

import dev.sim0n.modpack.binding.factory.SimpleBindingFactory;
import dev.sim0n.modpack.event.impl.game.key.KeyState;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;

/**
 * @author sim0n
 */
public class DefaultBindingSystem implements BindingSystem {
    @Getter
    private final Queue<Binding> bindings = new ArrayDeque<>();

    private final BindingFactory bindingFactory = SimpleBindingFactory.INSTANCE;

    @Override
    public Binding makeBinding(Runnable action, Set<Integer> requiredKeyCodes) {
        Binding binding = this.bindingFactory.makeBinding(action, requiredKeyCodes);

        this.bindings.add(binding);

        return binding;
    }

    @Override
    public boolean unbind(Binding binding) {
        return this.bindings.remove(binding);
    }

    @Override
    public boolean isBound(int key) {
        // TODO:
        return false;
    }

    @Override
    public void invokeKey(KeyState state, int code) {
        this.bindings.forEach(binding -> {
            switch (state) {
                case PRESSED -> binding.onKeyPress(code);
                case RELEASED -> binding.onKeyRelease(code);
            }
        });
    }
}
