package dev.goshi.omnimixin.api.event.entity;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.entity.Entity;

public record DespawnContext(Entity entity, Cancellable cancel) {
}
