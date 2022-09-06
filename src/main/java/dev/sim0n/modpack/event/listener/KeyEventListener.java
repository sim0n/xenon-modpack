package dev.sim0n.modpack.event.listener;

import dev.sim0n.modpack.ModPack;
import dev.sim0n.modpack.event.bus.handler.EventHandler;
import dev.sim0n.modpack.event.impl.game.key.KeyEvent;
import dev.sim0n.modpack.binding.BindingSystem;
import lombok.Getter;

/**
 * @author sim0n
 */
public class KeyEventListener implements EventHandler<KeyEvent> {
    private final ModPack modPack = ModPack.getInstance();
    private final BindingSystem bindingSystem = modPack.getBindingSystem();

    @Getter
    private final Class<? extends KeyEvent> type = KeyEvent.class;

    @Override
    public void invoke(KeyEvent event) {
        this.bindingSystem.invokeKey(event.getState(), event.getCode());
    }
}
