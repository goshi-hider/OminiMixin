package dev.goshi.omnimixin.api.event.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public record EquipContext(LivingEntity entity, EquipmentSlot slot, ItemStack oldStack, ItemStack newStack) {
}
