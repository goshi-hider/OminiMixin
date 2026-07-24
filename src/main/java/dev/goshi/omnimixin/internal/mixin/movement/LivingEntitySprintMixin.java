package dev.goshi.omnimixin.internal.mixin.movement;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.event.movement.PlaySprint;
import dev.goshi.omnimixin.api.event.movement.SprintContext;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
abstract class LivingEntitySprintMixin {

    @WrapMethod(method = "setSprinting(Z)V")
    private void omnimixin$wrapSetSprinting(boolean sprinting, Operation<Void> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        boolean wasSprinting = self.isSprinting();

        if (sprinting && !wasSprinting) {
            boolean allowed = PlaySprint.CAN_SPRINT.invoker().canSprint(new SprintContext(self));
            if (!allowed) {

                return;
            }
        }

        original.call(sprinting);

        if (sprinting != wasSprinting) {
            PlaySprint.ON_TOGGLE.invoker().onToggle(new SprintContext(self), sprinting);
        }
    }
}
