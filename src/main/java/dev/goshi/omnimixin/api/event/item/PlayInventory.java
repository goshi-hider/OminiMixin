package dev.goshi.omnimixin.api.event.item;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayInventory {

    private PlayInventory() {
    }

    @FunctionalInterface
    public interface SlotClick {
        void slotClick(SlotClickContext context);
    }

    public static final Event<SlotClick> SLOT_CLICK = EventFactory.createArrayBacked(
            SlotClick.class,
            listeners -> context -> {
                for (SlotClick listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.slotClick(context), "PlayInventory.SLOT_CLICK");
                }
            }
    );
}
