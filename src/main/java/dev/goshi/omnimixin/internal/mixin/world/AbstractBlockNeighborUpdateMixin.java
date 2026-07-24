package dev.goshi.omnimixin.internal.mixin.world;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.world.NeighborUpdateContext;
import dev.goshi.omnimixin.api.event.world.PlayBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractBlock.class)
abstract class AbstractBlockNeighborUpdateMixin {

    @WrapMethod(method = "neighborUpdate(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;Lnet/minecraft/util/math/BlockPos;Z)V")
    private void omnimixin$wrapNeighborUpdate(
            BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify,
            Operation<Void> original
    ) {
        Cancellable cancel = new Cancellable();
        PlayBlock.NEIGHBOR_UPDATE.invoker().neighborUpdate(
                new NeighborUpdateContext(world, pos, state, sourceBlock, sourcePos, cancel)
        );
        if (cancel.isCancelled()) {
            return;
        }
        original.call(state, world, pos, sourceBlock, sourcePos, notify);
    }
}
