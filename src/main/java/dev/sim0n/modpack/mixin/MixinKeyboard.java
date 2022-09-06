package dev.sim0n.modpack.mixin;

import dev.sim0n.modpack.ModPack;
import dev.sim0n.modpack.event.impl.game.key.KeyEvent;
import dev.sim0n.modpack.event.impl.game.key.KeyState;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author sim0n
 */
@Mixin(Keyboard.class)
public class MixinKeyboard {
    private final ModPack modPack = ModPack.getInstance();

    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo info) {
        var keyEvent = new KeyEvent(key, KeyState.values()[action]);

        this.modPack.getEventBus().publish(keyEvent);
    }
}
