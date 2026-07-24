package dev.goshi.omnimixin.internal.mixin.sync;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.sync.SyncedValue;
import dev.goshi.omnimixin.internal.sync.SyncedDataStore;
import dev.goshi.omnimixin.internal.sync.SyncedValueRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(Entity.class)
abstract class EntitySyncedDataMixin implements SyncedDataStore {

    @Unique
    private final Map<Identifier, Object> omnimixin$syncedValues = new ConcurrentHashMap<>();

    @Override
    public Map<Identifier, Object> omnimixin$syncedValues() {
        return this.omnimixin$syncedValues;
    }

    @WrapMethod(method = "saveSelfNbt(Lnet/minecraft/nbt/NbtCompound;)Z")
    private boolean omnimixin$writeSyncedValues(NbtCompound nbt, Operation<Boolean> original) {
        boolean result = original.call(nbt);

        if (this.omnimixin$syncedValues.isEmpty()) {
            return result;
        }

        NbtCompound synced = new NbtCompound();
        for (Map.Entry<Identifier, Object> entry : this.omnimixin$syncedValues.entrySet()) {

            SyncedValue<?> definition = SyncedValueRegistry.get(entry.getKey());
            if (definition == null) {
                continue;
            }
            synced.putString(entry.getKey().toString(), definition.type().encode(entry.getValue()));
        }

        if (!synced.isEmpty()) {
            nbt.put("OmniMixinSyncedValues", synced);
        }

        return result;
    }

    @WrapMethod(method = "readNbt(Lnet/minecraft/nbt/NbtCompound;)V")
    private void omnimixin$readSyncedValues(NbtCompound nbt, Operation<Void> original) {
        original.call(nbt);

        if (!nbt.contains("OmniMixinSyncedValues")) {
            return;
        }

        NbtCompound synced = nbt.getCompound("OmniMixinSyncedValues");
        for (String key : synced.getKeys()) {
            Identifier id = Identifier.tryParse(key);
            if (id == null) {
                continue;
            }
            SyncedValue<?> definition = SyncedValueRegistry.get(id);
            if (definition == null) {
                continue;
            }
            this.omnimixin$syncedValues.put(id, definition.type().decode(synced.getString(key)));
        }
    }
}
