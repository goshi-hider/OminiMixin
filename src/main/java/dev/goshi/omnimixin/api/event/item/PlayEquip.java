package dev.goshi.omnimixin.api.event.item;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayEquip {

    private PlayEquip() {
    }

    @FunctionalInterface
    public interface Change {
        void change(EquipContext context);
    }

    public static final Event<Change> CHANGE = EventFactory.createArrayBacked(
            Change.class,
            listeners -> context -> {
                for (Change listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.change(context), "PlayEquip.CHANGE");
                }
            }
    );
}
