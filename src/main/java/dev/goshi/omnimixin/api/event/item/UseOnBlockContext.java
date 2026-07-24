package dev.goshi.omnimixin.api.event.item;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;

public record UseOnBlockContext(ItemStack stack, ItemUsageContext vanillaContext, Cancellable cancel) {
}
