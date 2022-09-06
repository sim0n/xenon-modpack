package dev.sim0n.modpack.binding.impl;

import dev.sim0n.modpack.binding.Binding;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sim0n
 */
@Getter
@RequiredArgsConstructor
public class SimpleBinding implements Binding {
    private final Runnable action;

    private final Set<Integer> requiredKeyCodes;
    private final Set<Integer> pressedKeyCodes = new HashSet<>();

    @Override
    public void onKeyPress(int key) {
        if (!this.requiredKeyCodes.contains(key))
            return;

        this.pressedKeyCodes.add(key);

        if (this.canUseAction()) {
            this.action.run();
        }
    }

    @Override
    public void onKeyRelease(int key) {
        this.pressedKeyCodes.remove(key);
    }

    @Override
    public void bind(int... requiredKeyCodes) {
        this.requiredKeyCodes.clear();
        this.requiredKeyCodes.addAll(Arrays.stream(requiredKeyCodes).boxed().collect(Collectors.toSet()));
    }

    @Override
    public boolean canUseAction() {
        return this.requiredKeyCodes.size() == this.pressedKeyCodes.size();
    }
}
