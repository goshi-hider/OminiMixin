package dev.goshi.omnimixin.internal.sync;

import dev.goshi.OmniMixin;
import dev.goshi.omnimixin.api.sync.SyncedValue;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SyncedValueRegistry {

    private static final Map<Identifier, SyncedValue<?>> BY_ID = new ConcurrentHashMap<>();

    private SyncedValueRegistry() {
    }

    public static void register(SyncedValue<?> value) {
        SyncedValue<?> existing = BY_ID.putIfAbsent(value.id(), value);
        if (existing != null && existing != value) {
            OmniMixin.LOGGER.warn(
                    "[OmniMixin] Two SyncedValue.builder() calls used the same id '{}' - "
                            + "only the first one registered this session is in effect. "
                            + "Synced value ids must be unique per mod.",
                    value.id()
            );
        }
    }

    public static SyncedValue<?> get(Identifier id) {
        return BY_ID.get(id);
    }
}
