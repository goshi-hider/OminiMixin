package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public record DurabilityContext(LivingEntity victim, DamageSource source, float amount, Cancellable cancel) {
}
