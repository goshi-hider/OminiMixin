package dev.goshi.omnimixin.api.event.world;

import dev.goshi.omnimixin.api.common.MutableFloat;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;

public record MiningSpeedContext(PlayerEntity player, BlockState state, MutableFloat speed) {
}
