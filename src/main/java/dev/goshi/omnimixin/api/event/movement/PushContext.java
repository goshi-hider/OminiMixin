package dev.goshi.omnimixin.api.event.movement;

import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import net.minecraft.entity.Entity;

public record PushContext(Entity entity, Entity other, MutableFloat forceMultiplier, Cancellable cancel) {
}
