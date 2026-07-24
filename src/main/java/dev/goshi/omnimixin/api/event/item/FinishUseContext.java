package dev.goshi.omnimixin.api.event.item;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public record FinishUseContext(ItemStack stack, World world, LivingEntity user, Cancellable cancel) {
}
