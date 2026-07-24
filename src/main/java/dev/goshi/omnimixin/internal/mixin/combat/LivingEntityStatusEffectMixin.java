package dev.goshi.omnimixin.internal.mixin.combat;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.combat.PlayStatusEffect;
import dev.goshi.omnimixin.api.event.combat.StatusEffectApplyContext;
import dev.goshi.omnimixin.api.event.combat.StatusEffectExpireContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
abstract class LivingEntityStatusEffectMixin {

    @WrapMethod(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z")
    private boolean omnimixin$wrapAddStatusEffect(StatusEffectInstance effect, Entity source, Operation<Boolean> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        Cancellable cancel = new Cancellable();

        PlayStatusEffect.APPLY.invoker().apply(new StatusEffectApplyContext(self, effect, source, cancel));
        if (cancel.isCancelled()) {
            return false;
        }

        return original.call(effect, source);
    }

    @WrapMethod(method = "removeStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Z")
    private boolean omnimixin$wrapRemoveStatusEffect(RegistryEntry<StatusEffect> type, Operation<Boolean> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        Cancellable cancel = new Cancellable();

        PlayStatusEffect.EXPIRE.invoker().expire(new StatusEffectExpireContext(self, type, cancel));
        if (cancel.isCancelled()) {
            return false;
        }

        return original.call(type);
    }
}
