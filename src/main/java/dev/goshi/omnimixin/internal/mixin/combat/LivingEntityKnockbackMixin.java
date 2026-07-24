package dev.goshi.omnimixin.internal.mixin.combat;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import dev.goshi.omnimixin.api.event.combat.KnockbackContext;
import dev.goshi.omnimixin.api.event.combat.PlayKnockback;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
abstract class LivingEntityKnockbackMixin {

    @WrapMethod(method = "takeKnockback(DDD)V")
    private void omnimixin$wrapTakeKnockback(double strength, double x, double z, Operation<Void> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        Cancellable cancel = new Cancellable();
        MutableFloat forceMultiplier = new MutableFloat(1.0f);

        KnockbackContext context = new KnockbackContext(self, strength, x, z, forceMultiplier, cancel);
        PlayKnockback.PRE_APPLY.invoker().preApply(context);
        if (cancel.isCancelled()) {
            return;
        }

        PlayKnockback.MODIFY_FORCE.invoker().modifyForce(context);
        if (cancel.isCancelled()) {
            return;
        }

        double modifiedStrength = strength * forceMultiplier.get();
        original.call(modifiedStrength, x, z);
    }
}
