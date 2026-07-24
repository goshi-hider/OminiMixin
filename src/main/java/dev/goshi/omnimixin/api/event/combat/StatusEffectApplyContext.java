package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public record StatusEffectApplyContext(
        LivingEntity entity,
        StatusEffectInstance effect,
        Entity source,
        Cancellable cancel
) {
}
