package dev.goshi.omnimixin.internal.mixin.world;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.goshi.omnimixin.api.common.MutableInt;
import dev.goshi.omnimixin.api.event.world.PlayRedstone;
import dev.goshi.omnimixin.api.event.world.RedstonePowerContext;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractBlock.class)
abstract class AbstractBlockRedstoneMixin {

    @ModifyReturnValue(
            method = "getWeakRedstonePower(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)I",
            at = @At("RETURN")
    )
    private int omnimixin$modifyWeakPower(int original, BlockState state, BlockView world, BlockPos pos, Direction direction) {
        MutableInt power = new MutableInt(original);
        PlayRedstone.MODIFY_POWER.invoker().modify(new RedstonePowerContext(state, world, pos, direction, false, power));
        return power.get();
    }

    @ModifyReturnValue(
            method = "getStrongRedstonePower(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)I",
            at = @At("RETURN")
    )
    private int omnimixin$modifyStrongPower(int original, BlockState state, BlockView world, BlockPos pos, Direction direction) {
        MutableInt power = new MutableInt(original);
        PlayRedstone.MODIFY_POWER.invoker().modify(new RedstonePowerContext(state, world, pos, direction, true, power));
        return power.get();
    }
}
