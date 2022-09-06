package dev.sim0n.modpack.ui.tab.item.action.impl;

import dev.sim0n.modpack.ui.tab.item.action.KeyPressAction;
import lombok.RequiredArgsConstructor;

/**
 * @author sim0n
 */
@RequiredArgsConstructor
public class SimpleKeyPressAction implements KeyPressAction {
    private final int requiredKey;

    private final Runnable action;

    @Override
    public void handle(int key) {
        if (this.requiredKey == key) {
            action.run();
        }
    }
}

