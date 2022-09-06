package dev.sim0n.modpack.mixin;

import dev.sim0n.modpack.ModPack;
import dev.sim0n.modpack.util.helper.MCHelper;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author sim0n
 */
@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler implements MCHelper {
    private final ModPack modPack = ModPack.getInstance();

    @Inject(method = "onGameJoin", at = @At("RETURN"))
    public void onGameJoin(GameJoinS2CPacket packet, CallbackInfo info) {
        this.modPack.getModManager().getMods().forEach(mod -> {
            mod.setPlayer(mc.player);
            mod.setWorld(mc.world);
        });
    }

    @Inject(method = "onPlayerRespawn", at = @At("RETURN"))
    public void onPlayerRespawn(PlayerRespawnS2CPacket packet, CallbackInfo info) {
        this.modPack.getModManager().getMods().forEach(mod -> {
            mod.setPlayer(mc.player);
            mod.setWorld(mc.world);
        });
    }
}
