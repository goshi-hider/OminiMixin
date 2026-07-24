package dev.goshi.omnimixin.internal.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.event.entity.PlayEntity;
import dev.goshi.omnimixin.api.event.entity.TickContext;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
abstract class EntityTickMixin {

    @WrapMethod(method = "tick()V")
    private void omnimixin$wrapTick(Operation<Void> original) {
        Entity self = (Entity) (Object) this;

        PlayEntity.PRE_TICK.invoker().preTick(new TickContext(self));
        original.call();
        PlayEntity.POST_TICK.invoker().postTick(new TickContext(self));
    }
}
