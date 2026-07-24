package dev.goshi.omnimixin.api.event.movement;

import dev.goshi.omnimixin.api.common.MutableFloat;
import net.minecraft.entity.LivingEntity;

public record SpeedContext(LivingEntity entity, float currentBase, MutableFloat modifier) {
}
