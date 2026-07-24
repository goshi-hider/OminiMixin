package dev.goshi.omnimixin.internal.mixin.combat;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import dev.goshi.omnimixin.api.event.combat.AbsorptionChangeContext;
import dev.goshi.omnimixin.api.event.combat.DeathContext;
import dev.goshi.omnimixin.api.event.combat.HealContext;
import dev.goshi.omnimixin.api.event.combat.PlayHealth;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
abstract class LivingEntityHealthMixin {

    @WrapMethod(method = "heal(F)V")
    private void omnimixin$wrapHeal(float amount, Operation<Void> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        Cancellable cancel = new Cancellable();
        MutableFloat modifier = new MutableFloat(amount);

        PlayHealth.HEAL.invoker().heal(new HealContext(self, amount, modifier, cancel));
        if (cancel.isCancelled()) {
            return;
        }

        original.call(modifier.get());
    }

    @WrapMethod(method = "onDeath(Lnet/minecraft/entity/damage/DamageSource;)V")
    private void omnimixin$wrapOnDeath(DamageSource source, Operation<Void> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        Cancellable cancel = new Cancellable();

        PlayHealth.DEATH.invoker().death(new DeathContext(self, source, cancel));
        if (cancel.isCancelled()) {
            return;
        }

        original.call(source);
    }

    @WrapMethod(method = "setAbsorptionAmountUnclamped(F)V")
    private void omnimixin$wrapSetAbsorption(float absorptionAmount, Operation<Void> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        float oldAmount = self.getAbsorptionAmount();
        Cancellable cancel = new Cancellable();
        MutableFloat modifier = new MutableFloat(absorptionAmount);

        PlayHealth.ABSORPTION_CHANGE.invoker().change(
                new AbsorptionChangeContext(self, oldAmount, absorptionAmount, modifier, cancel)
        );
        if (cancel.isCancelled()) {
            return;
        }

        original.call(modifier.get());
    }
}
