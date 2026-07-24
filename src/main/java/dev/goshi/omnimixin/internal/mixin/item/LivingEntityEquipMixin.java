package dev.goshi.omnimixin.internal.mixin.item;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.event.item.EquipContext;
import dev.goshi.omnimixin.api.event.item.PlayEquip;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
abstract class LivingEntityEquipMixin {

    @WrapMethod(method = "onEquipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V")
    private void omnimixin$wrapOnEquipStack(
            EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, Operation<Void> original
    ) {
        LivingEntity self = (LivingEntity) (Object) this;
        original.call(slot, oldStack, newStack);
        PlayEquip.CHANGE.invoker().change(new EquipContext(self, slot, oldStack, newStack));
    }
}
