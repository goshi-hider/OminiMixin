package dev.goshi.omnimixin.api.event.entity;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;

public record SpawnContext(Entity entity, ServerWorld world, Cancellable cancel) {
}
