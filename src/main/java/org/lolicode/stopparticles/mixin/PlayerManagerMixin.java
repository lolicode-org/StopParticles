package org.lolicode.stopparticles.mixin;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import org.lolicode.stopparticles.Stopparticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerList.class)
public abstract class PlayerManagerMixin {
    @Unique
    private static final ViaAPI<?> viaAPI = Via.getAPI();
    @Unique
    private static final int serverVersion = viaAPI.getServerVersion().highestSupportedProtocolVersion().getVersion();

    @Inject(at = @At(value = "TAIL"), method = "placeNewPlayer")
    private void onPlayerJoin(Connection connection, ServerPlayer player, CommonListenerCookie clientData, CallbackInfo ci) {
        Stopparticles.noParticles.put(player.getUUID(), viaAPI.getPlayerVersion(player.getUUID()) < serverVersion);
    }
}
