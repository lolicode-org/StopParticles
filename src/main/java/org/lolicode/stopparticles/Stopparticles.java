package org.lolicode.stopparticles;

import net.fabricmc.api.ModInitializer;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Stopparticles implements ModInitializer {
    public static final ConcurrentHashMap<UUID, Boolean> noParticles = new ConcurrentHashMap<>();

    @Override
    public void onInitialize() {
    }
}
