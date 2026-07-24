package dev.goshi.omnimixin.internal.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import dev.goshi.omnimixin.api.event.combat.ArmorContext;
import dev.goshi.omnimixin.api.event.combat.DurabilityContext;
import dev.goshi.omnimixin.api.event.combat.PlayArmor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
abstract class LivingEntityArmorMixin {

    @ModifyReturnValue(method = "applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F", at = @At("RETURN"))
    private float omnimixin$modifyArmorProtection(float armorAdjustedDamage, DamageSource source, float incomingDamage) {
        LivingEntity self = (LivingEntity) (Object) this;
        MutableFloat modifier = new MutableFloat(armorAdjustedDamage);
        PlayArmor.MODIFY_PROTECTION.invoker().modify(
                new ArmorContext(self, source, incomingDamage, armorAdjustedDamage, modifier)
        );
        return modifier.get();
    }

    @WrapMethod(method = "damageArmor(Lnet/minecraft/entity/damage/DamageSource;F)V")
    private void omnimixin$wrapDamageArmor(DamageSource source, float amount, Operation<Void> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        Cancellable cancel = new Cancellable();
        PlayArmor.ON_DURABILITY_DAMAGE.invoker().onDurabilityDamage(
                new DurabilityContext(self, source, amount, cancel)
        );
        if (cancel.isCancelled()) {
            return;
        }
        original.call(source, amount);
    }
}
