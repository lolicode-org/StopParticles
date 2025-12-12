package org.lolicode.stopparticles.mixin;

import org.lolicode.stopparticles.Stopparticles;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerCommonPacketListenerImpl.class)
public abstract class ServerCommonNetworkHandlerMixin {
    @Inject(method = "send(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;)V", at = @At("HEAD"), cancellable = true)
    public void interceptParticlePackets(Packet<?> packet, ChannelFutureListener channelFutureListener, CallbackInfo ci) {
        if (packet instanceof ClientboundLevelParticlesPacket) {
            if ((Object) this instanceof ServerGamePacketListenerImpl playHandler) {
                if (Stopparticles.noParticles.getOrDefault(playHandler.player.getUUID(), true)) {
                    ci.cancel();
                }
            }
        }
    }
}