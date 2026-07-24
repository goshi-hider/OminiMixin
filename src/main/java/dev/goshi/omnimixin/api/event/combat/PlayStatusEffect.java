package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayStatusEffect {

    private PlayStatusEffect() {
    }

    @FunctionalInterface
    public interface Apply {
        void apply(StatusEffectApplyContext context);
    }

    @FunctionalInterface
    public interface Expire {
        void expire(StatusEffectExpireContext context);
    }

    public static final Event<Apply> APPLY = EventFactory.createArrayBacked(
            Apply.class,
            listeners -> context -> {
                for (Apply listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.apply(context), "PlayStatusEffect.APPLY");
                }
            }
    );

    public static final Event<Expire> EXPIRE = EventFactory.createArrayBacked(
            Expire.class,
            listeners -> context -> {
                for (Expire listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.expire(context), "PlayStatusEffect.EXPIRE");
                }
            }
    );
}
