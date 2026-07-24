package dev.goshi.omnimixin.internal.mixin.combat;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import dev.goshi.omnimixin.api.event.combat.DamageContext;
import dev.goshi.omnimixin.api.event.combat.PlayDamage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
abstract class LivingEntityDamageMixin {

    @WrapMethod(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z")
    private boolean omnimixin$wrapDamage(DamageSource source, float amount, Operation<Boolean> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        Cancellable cancel = new Cancellable();

        MutableFloat finalAmount = new MutableFloat(amount);
        DamageContext context = new DamageContext(self, source, amount, finalAmount, cancel);

        PlayDamage.PRE_CALCULATE.invoker().preCalculate(context);
        if (cancel.isCancelled()) {
            return false;
        }

        PlayDamage.MODIFY_AMOUNT.invoker().modifyAmount(context);
        if (cancel.isCancelled()) {
            return false;
        }

        boolean applied = original.call(source, finalAmount.get());

        PlayDamage.POST_APPLY.invoker().postApply(context, applied);

        return applied;
    }
}
