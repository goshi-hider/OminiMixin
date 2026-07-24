package dev.goshi.omnimixin.api.event.world;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayBreak {

    private PlayBreak() {
    }

    @FunctionalInterface
    public interface CalculateSpeed {
        void calculate(MiningSpeedContext context);
    }

    @FunctionalInterface
    public interface PreBreak {
        void preBreak(BreakContext context);
    }

    @FunctionalInterface
    public interface PostBreak {
        void postBreak(AfterBreakContext context);
    }

    public static final Event<CalculateSpeed> CALCULATE_SPEED = EventFactory.createArrayBacked(
            CalculateSpeed.class,
            listeners -> context -> {
                for (CalculateSpeed listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.calculate(context), "PlayBreak.CALCULATE_SPEED");
                }
            }
    );

    public static final Event<PreBreak> PRE_BREAK = EventFactory.createArrayBacked(
            PreBreak.class,
            listeners -> context -> {
                for (PreBreak listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.preBreak(context), "PlayBreak.PRE_BREAK");
                }
            }
    );

    public static final Event<PostBreak> POST_BREAK = EventFactory.createArrayBacked(
            PostBreak.class,
            listeners -> context -> {
                for (PostBreak listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.postBreak(context), "PlayBreak.POST_BREAK");
                }
            }
    );
}
