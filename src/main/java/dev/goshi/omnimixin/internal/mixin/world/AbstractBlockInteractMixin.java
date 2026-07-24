package dev.goshi.omnimixin.internal.mixin.world;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.world.BlockInteractContext;
import dev.goshi.omnimixin.api.event.world.PlayBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractBlock.class)
abstract class AbstractBlockInteractMixin {

    @WrapMethod(method = "onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;")
    private ActionResult omnimixin$wrapOnUse(
            BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit,
            Operation<ActionResult> original
    ) {
        Cancellable cancel = new Cancellable();
        PlayBlock.INTERACT.invoker().interact(new BlockInteractContext(world, pos, state, player, hit, cancel));
        if (cancel.isCancelled()) {
            return ActionResult.PASS;
        }
        return original.call(state, world, pos, player, hit);
    }
}
