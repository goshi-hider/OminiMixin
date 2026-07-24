package dev.goshi.omnimixin.api.event.entity;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayEntity {

    private PlayEntity() {
    }

    @FunctionalInterface
    public interface PreTick {
        void preTick(TickContext context);
    }

    @FunctionalInterface
    public interface PostTick {
        void postTick(TickContext context);
    }

    @FunctionalInterface
    public interface Despawn {
        void despawn(DespawnContext context);
    }

    public static final Event<PreTick> PRE_TICK = EventFactory.createArrayBacked(
            PreTick.class,
            listeners -> context -> {
                for (PreTick listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.preTick(context), "PlayEntity.PRE_TICK");
                }
            }
    );

    public static final Event<PostTick> POST_TICK = EventFactory.createArrayBacked(
            PostTick.class,
            listeners -> context -> {
                for (PostTick listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.postTick(context), "PlayEntity.POST_TICK");
                }
            }
    );

    public static final Event<Despawn> DESPAWN = EventFactory.createArrayBacked(
            Despawn.class,
            listeners -> context -> {
                for (Despawn listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.despawn(context), "PlayEntity.DESPAWN");
                }
            }
    );
}
