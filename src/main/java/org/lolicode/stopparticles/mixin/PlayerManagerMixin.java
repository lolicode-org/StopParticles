package org.lolicode.stopparticles.mixin;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import org.lolicode.stopparticles.Stopparticles;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
    @Unique
    private static final ViaAPI<?> viaAPI = Via.getAPI();
    @Unique
    private static final int serverVersion = viaAPI.getServerVersion().highestSupportedProtocolVersion().getVersion();

    @Inject(at = @At(value = "TAIL"), method = "onPlayerConnect")
    private void onPlayerJoin(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo ci) {
        Stopparticles.noParticles.put(player.getUuid(), viaAPI.getPlayerVersion(player.getUuid()) < serverVersion);
    }
}
