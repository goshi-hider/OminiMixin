package dev.goshi.omnimixin.api.event.entity;

import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;

public record TargetContext(
        MobEntity entity,
        LivingEntity oldTarget,
        MutableReference<LivingEntity> target,
        Cancellable cancel
) {
}
