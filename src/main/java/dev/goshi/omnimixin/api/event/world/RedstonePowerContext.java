package dev.goshi.omnimixin.api.event.world;

import dev.goshi.omnimixin.api.common.MutableInt;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public record RedstonePowerContext(
        BlockState state,
        BlockView world,
        BlockPos pos,
        Direction direction,
        boolean strong,
        MutableInt power
) {
}
