package dev.goshi.omnimixin.internal.mixin.world;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.world.AfterBreakContext;
import dev.goshi.omnimixin.api.event.world.BreakContext;
import dev.goshi.omnimixin.api.event.world.PlayBreak;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
abstract class BlockBreakMixin {

    @WrapMethod(method = "onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/block/BlockState;")
    private BlockState omnimixin$wrapOnBreak(
            World world, BlockPos pos, BlockState state, PlayerEntity player, Operation<BlockState> original
    ) {
        Cancellable cancel = new Cancellable();
        PlayBreak.PRE_BREAK.invoker().preBreak(new BreakContext(world, pos, state, player, cancel));
        if (cancel.isCancelled()) {
            return state;
        }
        return original.call(world, pos, state, player);
    }

    @WrapMethod(method = "afterBreak(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/item/ItemStack;)V")
    private void omnimixin$wrapAfterBreak(
            World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool,
            Operation<Void> original
    ) {
        Cancellable cancel = new Cancellable();
        PlayBreak.POST_BREAK.invoker().postBreak(
                new AfterBreakContext(world, player, pos, state, blockEntity, tool, cancel)
        );
        if (cancel.isCancelled()) {
            return;
        }
        original.call(world, player, pos, state, blockEntity, tool);
    }
}
