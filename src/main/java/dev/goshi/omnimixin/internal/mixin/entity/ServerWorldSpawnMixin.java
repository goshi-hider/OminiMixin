package dev.goshi.omnimixin.internal.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.entity.PlaySpawn;
import dev.goshi.omnimixin.api.event.entity.SpawnContext;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerWorld.class)
abstract class ServerWorldSpawnMixin {

    @WrapMethod(method = "spawnEntity(Lnet/minecraft/entity/Entity;)Z")
    private boolean omnimixin$wrapSpawnEntity(Entity entity, Operation<Boolean> original) {
        ServerWorld self = (ServerWorld) (Object) this;
        Cancellable cancel = new Cancellable();

        PlaySpawn.PRE_SPAWN.invoker().preSpawn(new SpawnContext(entity, self, cancel));
        if (cancel.isCancelled()) {
            PlaySpawn.POST_SPAWN.invoker().postSpawn(new SpawnContext(entity, self, cancel), false);
            return false;
        }

        boolean spawned = original.call(entity);
        PlaySpawn.POST_SPAWN.invoker().postSpawn(new SpawnContext(entity, self, cancel), spawned);
        return spawned;
    }
}
