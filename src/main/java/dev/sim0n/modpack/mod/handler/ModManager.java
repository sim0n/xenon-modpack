package dev.sim0n.modpack.mod.handler;

import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.mod.impl.move.Sprint;
import dev.sim0n.modpack.mod.impl.render.HUD;
import dev.sim0n.modpack.util.collection.map.ClassToInstanceMapBuilder;

import java.util.Collection;
import java.util.Optional;

/**
 * @author sim0n
 */
public final class ModManager {
    private final ClassToInstanceMapBuilder<Mod> mods = new ClassToInstanceMapBuilder<Mod>()
            .put(new Sprint())

            .put(new HUD())
            .build();

    public <T extends Mod> T getMod(Class<T> clazz) {
        return this.mods.getInstance(clazz);
    }

    /**
     * Finds a mod by the name
     * @param name The name of the mod to find
     * @return An optional of the mod, or an empty optional if no mod was found
     */
    public Optional<Mod> findByName(String name) {
        return this.mods.values().stream()
                .filter(mod -> mod.getLabel().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * @return A collection of all mods
     */
    public Collection<Mod> getMods() {
        return this.mods.values();
    }
}
