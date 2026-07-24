package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.api.common.MutableFloat;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public record ArmorContext(
        LivingEntity victim,
        DamageSource source,
        float incomingDamage,
        float armorAdjustedDamage,
        MutableFloat modifier
) {
}
