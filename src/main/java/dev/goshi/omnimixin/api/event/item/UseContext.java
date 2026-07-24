package dev.goshi.omnimixin.api.event.item;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public record UseContext(ItemStack stack, World world, PlayerEntity user, Hand hand, Cancellable cancel) {
}
