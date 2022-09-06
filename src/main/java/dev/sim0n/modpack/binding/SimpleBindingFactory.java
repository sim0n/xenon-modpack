package dev.sim0n.modpack.binding;

import dev.sim0n.modpack.binding.impl.SimpleBinding;

import java.util.Set;

/**
 * @author sim0n
 */
public enum SimpleBindingFactory implements BindingFactory {
    INSTANCE;

    private final BindingFactory innerBindingFactory = new PartitioningBindingFactory(SimpleBinding::new);

    @Override
    public Binding makeBinding(Runnable action, Set<Integer> requiredKeyCodes) {
        return this.innerBindingFactory.makeBinding(action, requiredKeyCodes);
    }
}
