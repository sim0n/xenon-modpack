package dev.sim0n.modpack.mod.impl.render;

import dev.sim0n.modpack.event.impl.render.RenderScreenEvent;
import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.mod.ModCategory;
import dev.sim0n.modpack.mod.ModManifest;

/**
 * @author sim0n
 *
 * Draws the heads-up display
 */
@ModManifest(label = "HUD", category = ModCategory.RENDER)
public class HUD extends Mod {
    public HUD() {
        this.registerEvent(RenderScreenEvent.class, event -> {
            mc.textRenderer.draw(event.getMatrices(), "Hello World!", 2, 32, 0xFFFFFF);
        });
    }
}
