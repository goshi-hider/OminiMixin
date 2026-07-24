package dev.goshi.omnimixin.api.event.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public record BlockLootContext(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, List<ItemStack> drops) {
}
