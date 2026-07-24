package dev.goshi.omnimixin.internal.mixin.item;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.item.PlayInventory;
import dev.goshi.omnimixin.api.event.item.SlotClickContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ScreenHandler.class)
abstract class ScreenHandlerSlotClickMixin {

    @WrapMethod(method = "onSlotClick(IILnet/minecraft/screen/slot/SlotActionType;Lnet/minecraft/entity/player/PlayerEntity;)V")
    private void omnimixin$wrapOnSlotClick(
            int slotIndex, int button, SlotActionType actionType, PlayerEntity player, Operation<Void> original
    ) {
        ScreenHandler self = (ScreenHandler) (Object) this;
        Cancellable cancel = new Cancellable();
        PlayInventory.SLOT_CLICK.invoker().slotClick(
                new SlotClickContext(self, slotIndex, button, actionType, player, cancel)
        );
        if (cancel.isCancelled()) {
            return;
        }
        original.call(slotIndex, button, actionType, player);
    }
}
