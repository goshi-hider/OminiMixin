package dev.goshi.omnimixin.internal.mixin.movement;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.goshi.omnimixin.api.common.MutableFloat;
import dev.goshi.omnimixin.api.event.movement.PlaySpeed;
import dev.goshi.omnimixin.api.event.movement.SpeedContext;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
abstract class LivingEntitySpeedMixin {

    @ModifyReturnValue(method = "getMovementSpeed()F", at = @At("RETURN"))
    private float omnimixin$modifyGroundSpeed(float original) {
        LivingEntity self = (LivingEntity) (Object) this;
        MutableFloat modifier = new MutableFloat(original);
        PlaySpeed.MODIFY_GROUND.invoker().modify(new SpeedContext(self, original, modifier));
        return modifier.get();
    }
}
