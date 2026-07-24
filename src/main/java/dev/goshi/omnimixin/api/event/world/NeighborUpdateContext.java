package dev.goshi.omnimixin.api.event.world;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public record NeighborUpdateContext(
        World world,
        BlockPos pos,
        BlockState state,
        Block sourceBlock,
        BlockPos sourcePos,
        Cancellable cancel
) {
}
