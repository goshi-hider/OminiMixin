package dev.goshi.omnimixin.api.event.world;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public record BlockInteractContext(
        World world,
        BlockPos pos,
        BlockState state,
        PlayerEntity player,
        BlockHitResult hit,
        Cancellable cancel
) {
}
