package dev.goshi.omnimixin.internal.network;

import dev.goshi.omnimixin.api.sync.SyncedValue;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public final class OmniNetworkSync {

    private OmniNetworkSync() {
    }

    public static void init() {
        PayloadTypeRegistry.playS2C().register(SyncedValuePayload.ID, SyncedValuePayload.CODEC);
    }

    public static <T> void dispatch(Entity entity, SyncedValue<T> value, T newValue) {
        if (!(entity.getWorld() instanceof ServerWorld)) {
            return;
        }

        SyncedValuePayload payload = new SyncedValuePayload(
                entity.getId(),
                value.id(),
                (byte) value.type().ordinal(),
                value.type().encode(newValue)
        );

        for (ServerPlayerEntity tracking : PlayerLookup.tracking(entity)) {
            if (isWithinSyncRange(tracking, entity, value.syncRange())) {
                ServerPlayNetworking.send(tracking, payload);
            }
        }

        if (entity instanceof ServerPlayerEntity self) {
            ServerPlayNetworking.send(self, payload);
        }
    }

    private static boolean isWithinSyncRange(ServerPlayerEntity player, Entity entity, double syncRange) {
        if (syncRange <= 0) {
            return true;
        }
        return player.squaredDistanceTo(entity) <= syncRange * syncRange;
    }
}
