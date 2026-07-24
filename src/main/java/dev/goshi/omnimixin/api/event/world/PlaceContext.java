package dev.goshi.omnimixin.api.event.world;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public record PlaceContext(
        World world,
        BlockPos pos,
        BlockState state,
        LivingEntity placer,
        ItemStack itemStack,
        Cancellable cancel
) {
}
