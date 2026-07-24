package dev.goshi.omnimixin.internal.mixin.movement;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import dev.goshi.omnimixin.api.event.movement.JumpContext;
import dev.goshi.omnimixin.api.event.movement.PlayJump;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
abstract class LivingEntityJumpMixin {

    @WrapMethod(method = "jump()V")
    private void omnimixin$wrapJump(Operation<Void> original) {
        LivingEntity self = (LivingEntity) (Object) this;
        Cancellable cancel = new Cancellable();

        PlayJump.PRE_JUMP.invoker().preJump(new JumpContext(self, cancel, new MutableFloat(0f)));
        if (cancel.isCancelled()) {
            return;
        }

        Vec3d before = self.getVelocity();
        original.call();
        Vec3d after = self.getVelocity();

        float verticalImpulse = (float) (after.y - before.y);
        MutableFloat impulse = new MutableFloat(verticalImpulse);
        JumpContext modifyContext = new JumpContext(self, cancel, impulse);
        PlayJump.MODIFY_IMPULSE.invoker().modifyImpulse(modifyContext);

        if (impulse.get() != verticalImpulse) {
            self.setVelocity(after.x, before.y + impulse.get(), after.z);
        }

        PlayJump.POST_JUMP.invoker().postJump(modifyContext);
    }
}
