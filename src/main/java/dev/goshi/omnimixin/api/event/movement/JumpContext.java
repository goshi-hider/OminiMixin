package dev.goshi.omnimixin.api.event.movement;

import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import net.minecraft.entity.LivingEntity;

public record JumpContext(LivingEntity entity, Cancellable cancel, MutableFloat verticalImpulse) {
}
