package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public record StatusEffectExpireContext(LivingEntity entity, RegistryEntry<StatusEffect> effect, Cancellable cancel) {
}
