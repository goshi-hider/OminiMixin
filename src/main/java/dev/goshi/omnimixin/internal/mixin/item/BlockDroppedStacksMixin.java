package dev.goshi.omnimixin.internal.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.goshi.omnimixin.api.event.item.BlockLootContext;
import dev.goshi.omnimixin.api.event.item.PlayLoot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(Block.class)
abstract class BlockDroppedStacksMixin {

    @ModifyReturnValue(
            method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;)Ljava/util/List;",
            at = @At("RETURN")
    )
    private static List<ItemStack> omnimixin$modifyDroppedStacks(
            List<ItemStack> original, BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity
    ) {
        PlayLoot.MODIFY_DROPS.invoker().modify(new BlockLootContext(state, world, pos, blockEntity, original));
        return original;
    }
}
