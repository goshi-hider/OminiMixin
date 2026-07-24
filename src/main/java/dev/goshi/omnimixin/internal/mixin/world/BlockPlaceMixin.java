package dev.goshi.omnimixin.internal.mixin.world;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.world.PlaceContext;
import dev.goshi.omnimixin.api.event.world.PlayBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
abstract class BlockPlaceMixin {

    @WrapMethod(method = "onPlaced(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)V")
    private void omnimixin$wrapOnPlaced(
            World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack,
            Operation<Void> original
    ) {
        Cancellable cancel = new Cancellable();
        PlayBlock.PLACE.invoker().place(new PlaceContext(world, pos, state, placer, itemStack, cancel));
        if (cancel.isCancelled()) {
            return;
        }
        original.call(world, pos, state, placer, itemStack);
    }
}
