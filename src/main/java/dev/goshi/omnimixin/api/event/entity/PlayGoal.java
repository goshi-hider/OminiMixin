package dev.goshi.omnimixin.api.event.entity;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayGoal {

    private PlayGoal() {
    }

    @FunctionalInterface
    public interface RegisterGoal {
        void registerGoal(GoalRegistrationContext context);
    }

    @FunctionalInterface
    public interface ModifyTarget {
        void modifyTarget(TargetContext context);
    }

    public static final Event<RegisterGoal> REGISTER_GOALS = EventFactory.createArrayBacked(
            RegisterGoal.class,
            listeners -> context -> {
                for (RegisterGoal listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.registerGoal(context), "PlayGoal.REGISTER_GOALS");
                }
            }
    );

    public static final Event<ModifyTarget> MODIFY_TARGET = EventFactory.createArrayBacked(
            ModifyTarget.class,
            listeners -> context -> {
                for (ModifyTarget listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.modifyTarget(context), "PlayGoal.MODIFY_TARGET");
                }
            }
    );
}
