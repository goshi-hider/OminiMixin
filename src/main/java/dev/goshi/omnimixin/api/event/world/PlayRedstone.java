package dev.goshi.omnimixin.api.event.world;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayRedstone {

    private PlayRedstone() {
    }

    @FunctionalInterface
    public interface ModifyPower {
        void modify(RedstonePowerContext context);
    }

    public static final Event<ModifyPower> MODIFY_POWER = EventFactory.createArrayBacked(
            ModifyPower.class,
            listeners -> context -> {
                for (ModifyPower listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.modify(context), "PlayRedstone.MODIFY_POWER");
                }
            }
    );
}
