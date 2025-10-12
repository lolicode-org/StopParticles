package org.lolicode.stopparticles.mixin;

import org.lolicode.stopparticles.Stopparticles;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.server.network.ServerCommonNetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerCommonNetworkHandler.class)
public abstract class ServerCommonNetworkHandlerMixin {
    @Inject(method = "send", at = @At("HEAD"), cancellable = true)
    public void interceptParticlePackets(Packet<?> packet, ChannelFutureListener channelFutureListener, CallbackInfo ci) {
        if (packet instanceof ParticleS2CPacket) {
            if ((Object) this instanceof ServerPlayNetworkHandler playHandler) {
                if (Stopparticles.noParticles.getOrDefault(playHandler.player.getUuid(), true)) {
                    ci.cancel();
                }
            }
        }
    }
}