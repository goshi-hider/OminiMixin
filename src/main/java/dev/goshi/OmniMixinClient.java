package dev.goshi;

import dev.goshi.omnimixin.api.sync.SyncedValue;
import dev.goshi.omnimixin.internal.network.SyncedValuePayload;
import dev.goshi.omnimixin.internal.sync.SyncedDataStore;
import dev.goshi.omnimixin.internal.sync.SyncedValueRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;

public final class OmniMixinClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(SyncedValuePayload.ID, (payload, context) ->
                context.client().execute(() -> {
                    ClientWorld world = context.client().world;
                    if (world == null) {
                        return;
                    }

                    Entity entity = world.getEntityById(payload.entityId());
                    if (entity == null) {
                        return;
                    }

                    SyncedValue<?> definition = SyncedValueRegistry.get(payload.valueId());
                    if (definition == null) {

                        return;
                    }

                    Object decoded = definition.type().decode(payload.encodedValue());
                    ((SyncedDataStore) entity).omnimixin$syncedValues().put(payload.valueId(), decoded);
                })
        );

        OmniMixin.LOGGER.info("OmniMixin client loaded - synced value receiver is live.");
    }
}
