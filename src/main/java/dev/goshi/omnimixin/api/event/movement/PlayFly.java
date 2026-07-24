package dev.goshi.omnimixin.api.event.movement;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayFly {

    private PlayFly() {
    }

    @FunctionalInterface
    public interface ModifyFlightSpeed {
        void modify(FlySpeedContext context);
    }

    public static final Event<ModifyFlightSpeed> MODIFY_FLIGHT_SPEED = EventFactory.createArrayBacked(
            ModifyFlightSpeed.class,
            listeners -> context -> {
                for (ModifyFlightSpeed listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.modify(context), "PlayFly.MODIFY_FLIGHT_SPEED");
                }
            }
    );
}
