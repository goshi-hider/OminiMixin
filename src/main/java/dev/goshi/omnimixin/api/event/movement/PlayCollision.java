package dev.goshi.omnimixin.api.event.movement;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayCollision {

    private PlayCollision() {
    }

    @FunctionalInterface
    public interface EntityPush {
        void push(PushContext context);
    }

    public static final Event<EntityPush> ENTITY_PUSH = EventFactory.createArrayBacked(
            EntityPush.class,
            listeners -> context -> {
                for (EntityPush listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.push(context), "PlayCollision.ENTITY_PUSH");
                }
            }
    );
}
