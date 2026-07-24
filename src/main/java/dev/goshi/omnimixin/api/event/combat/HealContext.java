package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import net.minecraft.entity.LivingEntity;

public record HealContext(LivingEntity entity, float originalAmount, MutableFloat modifier, Cancellable cancel) {
}
