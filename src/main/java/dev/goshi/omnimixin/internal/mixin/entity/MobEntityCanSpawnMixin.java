package dev.goshi.omnimixin.internal.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.event.entity.PlaySpawn;
import dev.goshi.omnimixin.api.event.entity.SpawnRulesContext;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MobEntity.class)
abstract class MobEntityCanSpawnMixin {

    @WrapMethod(method = "canSpawn(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/entity/SpawnReason;)Z")
    private boolean omnimixin$wrapCanSpawn(WorldAccess world, SpawnReason spawnReason, Operation<Boolean> original) {
        MobEntity self = (MobEntity) (Object) this;

        if (!original.call(world, spawnReason)) {
            return false;
        }

        return PlaySpawn.CHECK_RULES.invoker().checkRules(new SpawnRulesContext(self, world, spawnReason));
    }
}
