package dev.goshi.omnimixin.internal.mixin.movement;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.common.MutableFloat;
import dev.goshi.omnimixin.api.event.movement.PlayCollision;
import dev.goshi.omnimixin.api.event.movement.PushContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
abstract class EntityPushMixin {

    @WrapMethod(method = "pushAwayFrom(Lnet/minecraft/entity/Entity;)V")
    private void omnimixin$wrapPushAwayFrom(Entity other, Operation<Void> original) {
        Entity self = (Entity) (Object) this;
        Cancellable cancel = new Cancellable();
        MutableFloat forceMultiplier = new MutableFloat(1.0f);

        PlayCollision.ENTITY_PUSH.invoker().push(new PushContext(self, other, forceMultiplier, cancel));
        if (cancel.isCancelled()) {
            return;
        }

        Vec3d before = self.getVelocity();
        original.call(other);

        float multiplier = forceMultiplier.get();
        if (multiplier != 1.0f) {
            Vec3d after = self.getVelocity();
            double dx = (after.x - before.x) * multiplier;
            double dy = (after.y - before.y) * multiplier;
            double dz = (after.z - before.z) * multiplier;
            self.setVelocity(before.x + dx, before.y + dy, before.z + dz);
        }
    }
}
