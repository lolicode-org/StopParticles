package org.lolicode.stopparticles.mixin;

import org.lolicode.stopparticles.Stopparticles;
import net.minecraft.network.DisconnectionInfo;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Inject(at = @At(value = "TAIL"), method = "onDisconnected")
    private void onPlayerLeave(DisconnectionInfo info, CallbackInfo ci) {
        if (player != null) Stopparticles.noParticles.remove(player.getUuid());
    }
}
