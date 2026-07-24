package dev.goshi.omnimixin.api.event.movement;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlaySpeed {

    private PlaySpeed() {
    }

    @FunctionalInterface
    public interface ModifyGround {
        void modify(SpeedContext context);
    }

    public static final Event<ModifyGround> MODIFY_GROUND = EventFactory.createArrayBacked(
            ModifyGround.class,
            listeners -> context -> {
                for (ModifyGround listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.modify(context), "PlaySpeed.MODIFY_GROUND");
                }
            }
    );
}
