package dev.goshi.omnimixin.api.event.movement;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlaySprint {

    private PlaySprint() {
    }

    @FunctionalInterface
    public interface CanSprint {

        boolean canSprint(SprintContext context);
    }

    @FunctionalInterface
    public interface OnToggle {
        void onToggle(SprintContext context, boolean sprinting);
    }

    public static final Event<CanSprint> CAN_SPRINT = EventFactory.createArrayBacked(
            CanSprint.class,
            listeners -> context -> {
                for (CanSprint listener : listeners) {
                    boolean[] allowed = {true};
                    OmniMixinLog.safeRun(() -> allowed[0] = listener.canSprint(context), "PlaySprint.CAN_SPRINT");
                    if (!allowed[0]) {
                        return false;
                    }
                }
                return true;
            }
    );

    public static final Event<OnToggle> ON_TOGGLE = EventFactory.createArrayBacked(
            OnToggle.class,
            listeners -> (context, sprinting) -> {
                for (OnToggle listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.onToggle(context, sprinting), "PlaySprint.ON_TOGGLE");
                }
            }
    );
}
