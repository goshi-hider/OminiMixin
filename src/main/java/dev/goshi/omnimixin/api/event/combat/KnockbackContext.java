package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import net.minecraft.entity.LivingEntity;

public record KnockbackContext(
        LivingEntity entity,
        double originalStrength,
        double x,
        double z,
        MutableFloat forceMultiplier,
        Cancellable cancel
) {
}
