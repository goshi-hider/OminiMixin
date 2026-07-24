package dev.goshi.omnimixin.api.sync;

import dev.goshi.omnimixin.internal.network.OmniNetworkSync;
import dev.goshi.omnimixin.internal.sync.SyncedDataStore;
import dev.goshi.omnimixin.internal.sync.SyncedValueRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.util.Objects;

public final class SyncedValue<T> {

    private final Identifier id;
    private final SyncedType type;
    private final T defaultValue;
    private final double syncRange;

    private SyncedValue(Identifier id, SyncedType type, T defaultValue, double syncRange) {
        this.id = id;
        this.type = type;
        this.defaultValue = defaultValue;
        this.syncRange = syncRange;
    }

    public static <T> Builder<T> builder(Identifier id) {
        return new Builder<>(id);
    }

    public Identifier id() {
        return this.id;
    }

    public T defaultValue() {
        return this.defaultValue;
    }

    public double syncRange() {
        return this.syncRange;
    }

    public SyncedType type() {
        return this.type;
    }

    @SuppressWarnings("unchecked")
    public T get(Entity entity) {
        Object stored = ((SyncedDataStore) entity).omnimixin$syncedValues().get(this.id);
        return stored != null ? (T) stored : this.defaultValue;
    }

    public void set(Entity entity, T value) {
        ((SyncedDataStore) entity).omnimixin$syncedValues().put(this.id, value);
        OmniNetworkSync.dispatch(entity, this, value);
    }

    public static final class Builder<T> {

        private final Identifier id;
        private SyncedType type;
        private T defaultValue;
        private double syncRange = 64.0;

        private Builder(Identifier id) {
            this.id = Objects.requireNonNull(id, "id");
        }

        public Builder<T> type(SyncedType type) {
            this.type = type;
            return this;
        }

        public Builder<T> defaultValue(T defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder<T> syncRange(double syncRange) {
            this.syncRange = syncRange;
            return this;
        }

        public SyncedValue<T> build() {
            Objects.requireNonNull(this.type, "SyncedValue.builder(" + this.id + ") is missing .type(...)");
            SyncedValue<T> value = new SyncedValue<>(this.id, this.type, this.defaultValue, this.syncRange);
            SyncedValueRegistry.register(value);
            return value;
        }
    }
}
