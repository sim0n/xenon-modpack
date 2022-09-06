package dev.sim0n.modpack.mixin;

import dev.sim0n.modpack.ui.tab.TabUI;
import dev.sim0n.modpack.util.once.CallOnce;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author sim0n
 */
@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    private final CallOnce tabInitializer = new CallOnce(TabUI.INSTANCE::init);

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        this.tabInitializer.call();
    }
}
