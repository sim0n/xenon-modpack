package dev.sim0n.modpack;

import dev.sim0n.modpack.event.bus.EventBus;
import dev.sim0n.modpack.event.bus.dispatcher.impl.ImmediateDispatcher;
import dev.sim0n.modpack.event.bus.registry.DefaultSubscriptionRegistry;
import dev.sim0n.modpack.event.listener.KeyEventListener;
import dev.sim0n.modpack.binding.BindingSystem;
import dev.sim0n.modpack.binding.DefaultBindingSystem;
import dev.sim0n.modpack.mod.handler.ModManager;
import lombok.Getter;

/**
 * @author sim0n
 */
@Getter
public class ModPack {
    @Getter
    private static ModPack instance;

    private final EventBus eventBus = EventBus.builder()
            .withSubscriptionRegistry(new DefaultSubscriptionRegistry())
            .withDispatcher(ImmediateDispatcher.INSTANCE);

    private final BindingSystem bindingSystem = new DefaultBindingSystem();
    private ModManager modManager;

    public ModPack() {
        instance = this;
    }

    public void init() {
        this.registerManagers();
        this.registerListeners();
    }

    private void registerManagers() {
        this.modManager = new ModManager();
    }

    private void registerListeners() {
        this.eventBus.subscribe(new KeyEventListener());
    }
}
