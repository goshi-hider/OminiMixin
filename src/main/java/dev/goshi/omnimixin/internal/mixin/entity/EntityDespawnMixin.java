package dev.goshi.omnimixin.internal.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.entity.DespawnContext;
import dev.goshi.omnimixin.api.event.entity.PlayEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
abstract class EntityDespawnMixin {

    @WrapMethod(method = "discard()V")
    private void omnimixin$wrapDiscard(Operation<Void> original) {
        Entity self = (Entity) (Object) this;
        Cancellable cancel = new Cancellable();

        PlayEntity.DESPAWN.invoker().despawn(new DespawnContext(self, cancel));
        if (cancel.isCancelled()) {
            return;
        }

        original.call();
    }
}
