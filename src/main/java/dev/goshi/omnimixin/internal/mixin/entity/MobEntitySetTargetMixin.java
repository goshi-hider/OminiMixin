package dev.goshi.omnimixin.internal.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableReference;
import dev.goshi.omnimixin.api.event.entity.PlayGoal;
import dev.goshi.omnimixin.api.event.entity.TargetContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MobEntity.class)
abstract class MobEntitySetTargetMixin {

    @WrapMethod(method = "setTarget(Lnet/minecraft/entity/LivingEntity;)V")
    private void omnimixin$wrapSetTarget(LivingEntity target, Operation<Void> original) {
        MobEntity self = (MobEntity) (Object) this;
        LivingEntity oldTarget = self.getTarget();
        Cancellable cancel = new Cancellable();
        MutableReference<LivingEntity> mutableTarget = new MutableReference<>(target);

        PlayGoal.MODIFY_TARGET.invoker().modifyTarget(
                new TargetContext(self, oldTarget, mutableTarget, cancel)
        );
        if (cancel.isCancelled()) {
            return;
        }

        original.call(mutableTarget.get());
    }
}
