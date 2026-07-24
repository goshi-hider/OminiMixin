package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public record DamageContext(
        LivingEntity victim,
        DamageSource source,
        float originalDamage,
        MutableFloat finalDamage,
        Cancellable cancel
) {
}
