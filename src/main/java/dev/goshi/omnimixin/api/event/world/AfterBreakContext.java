package dev.goshi.omnimixin.api.event.world;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public record AfterBreakContext(
        World world,
        PlayerEntity player,
        BlockPos pos,
        BlockState state,
        BlockEntity blockEntity,
        ItemStack tool,
        Cancellable cancel
) {
}
