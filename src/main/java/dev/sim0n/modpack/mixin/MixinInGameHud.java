package dev.sim0n.modpack.mixin;

import dev.sim0n.modpack.ModPack;
import dev.sim0n.modpack.event.impl.render.RenderScreenEvent;
import dev.sim0n.modpack.event.impl.render.RenderTickEvent;
import dev.sim0n.modpack.util.helper.MCHelper;
import dev.sim0n.modpack.util.math.type.DVec2;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author sim0n
 */
@Mixin(InGameHud.class)
public class MixinInGameHud implements MCHelper {
    private final ModPack modPack = ModPack.getInstance();
    private long lastSystemTime;
    private int renderTick;

    @Inject(method = "render", at = @At("RETURN"))
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        var now = System.currentTimeMillis();
        var window = mc.getWindow();
        var screenSize = new DVec2(window.getScaledWidth(), window.getScaledHeight());

        this.modPack.getEventBus().publish(new RenderScreenEvent(matrices, screenSize));

        if (now > this.lastSystemTime) {
            this.modPack.getEventBus().publish(new RenderTickEvent(++this.renderTick));
            this.lastSystemTime = now + 16L;
        }
    }
}
