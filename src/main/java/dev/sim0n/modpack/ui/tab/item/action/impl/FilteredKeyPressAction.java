package dev.sim0n.modpack.ui.tab.item.action.impl;

import dev.sim0n.modpack.ui.tab.item.action.KeyPressAction;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

/**
 * @author sim0n
 */
@RequiredArgsConstructor
public class FilteredKeyPressAction implements KeyPressAction {
    private final int requiredKey;
    private final Runnable action;
    private final Supplier<Boolean> filter;

    @Override
    public void handle(int key) {
        if (this.requiredKey == key && this.filter.get()) {
            action.run();
        }
    }
}
