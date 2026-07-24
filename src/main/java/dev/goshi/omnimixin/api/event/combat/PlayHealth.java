package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayHealth {

    private PlayHealth() {
    }

    @FunctionalInterface
    public interface Heal {
        void heal(HealContext context);
    }

    @FunctionalInterface
    public interface Death {
        void death(DeathContext context);
    }

    @FunctionalInterface
    public interface AbsorptionChange {
        void change(AbsorptionChangeContext context);
    }

    public static final Event<Heal> HEAL = EventFactory.createArrayBacked(
            Heal.class,
            listeners -> context -> {
                for (Heal listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.heal(context), "PlayHealth.HEAL");
                }
            }
    );

    public static final Event<Death> DEATH = EventFactory.createArrayBacked(
            Death.class,
            listeners -> context -> {
                for (Death listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.death(context), "PlayHealth.DEATH");
                }
            }
    );

    public static final Event<AbsorptionChange> ABSORPTION_CHANGE = EventFactory.createArrayBacked(
            AbsorptionChange.class,
            listeners -> context -> {
                for (AbsorptionChange listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.change(context), "PlayHealth.ABSORPTION_CHANGE");
                }
            }
    );
}
