package dev.sim0n.modpack.mod.impl.move;

import dev.sim0n.modpack.event.impl.player.PlayerMovementTickEvent;
import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.mod.ModCategory;
import dev.sim0n.modpack.mod.ModManifest;
import dev.sim0n.modpack.property.impl.BoolProperty;
import dev.sim0n.modpack.property.impl.IntProperty;
import dev.sim0n.modpack.property.impl.MultiOptionProperty;
import dev.sim0n.modpack.property.impl.OptionProperty;
import dev.sim0n.modpack.property.impl.range.IntRangeProperty;
import org.lwjgl.glfw.GLFW;

/**
 * @author sim0n
 *
 * Automatically sprints
 */
@ModManifest(label = "Sprint", category = ModCategory.MOVE)
public class Sprint extends Mod {
    private final BoolProperty innerTest = new BoolProperty("innerTest", false);

    private final BoolProperty test = new BoolProperty("test", false)
            .addChild(innerTest)
            .buildAndBind(this);

    private final IntProperty test2 = new IntProperty("test2", 3, 0, 10)
            .buildAndBind(this);

    private final IntRangeProperty test3 = new IntRangeProperty("test3", 2, 8, 1,10)
            .buildAndBind(this);

    private final OptionProperty test4 = new OptionProperty("test4", 0, "test", "test2", "test3")
            .buildAndBind(this);

    private final MultiOptionProperty test5 = new MultiOptionProperty("test5", new int[] {0, 1}, "test", "test2", "test3")
            .buildAndBind(this);

    public Sprint() {
        this.getBinding().bind(GLFW.GLFW_KEY_Y);

        this.registerEvent(PlayerMovementTickEvent.class, event -> {
            this.player.setSprinting(true);
        });
    }
}
