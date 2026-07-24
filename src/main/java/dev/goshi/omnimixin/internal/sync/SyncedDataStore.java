package dev.goshi.omnimixin.internal.sync;

import net.minecraft.util.Identifier;

import java.util.Map;

public interface SyncedDataStore {

    Map<Identifier, Object> omnimixin$syncedValues();
}
