package dev.sim0n.modpack.mod;

import dev.sim0n.modpack.trait.Labeled;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author sim0n
 */
@RequiredArgsConstructor
public enum ModCategory implements Labeled {
    MOVE("Movement"),
    RENDER("Render");

    @Getter
    private final String label;
}
