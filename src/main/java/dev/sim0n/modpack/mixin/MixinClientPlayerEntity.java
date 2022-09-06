package dev.sim0n.modpack.mixin;

import dev.sim0n.modpack.ModPack;
import dev.sim0n.modpack.event.impl.player.PlayerMovementTickEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author sim0n
 */
@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    private final ModPack modPack = ModPack.getInstance();

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void tickMovement(CallbackInfo info) {
        this.modPack.getEventBus().publish(new PlayerMovementTickEvent());
    }
}
