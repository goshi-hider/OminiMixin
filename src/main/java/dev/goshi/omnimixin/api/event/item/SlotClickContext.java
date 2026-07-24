package dev.goshi.omnimixin.api.event.item;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;

public record SlotClickContext(
        ScreenHandler handler,
        int slotIndex,
        int button,
        SlotActionType actionType,
        PlayerEntity player,
        Cancellable cancel
) {
}
