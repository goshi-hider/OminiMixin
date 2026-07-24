package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayKnockback {

    private PlayKnockback() {
    }

    @FunctionalInterface
    public interface PreApply {
        void preApply(KnockbackContext context);
    }

    @FunctionalInterface
    public interface ModifyForce {
        void modifyForce(KnockbackContext context);
    }

    public static final Event<PreApply> PRE_APPLY = EventFactory.createArrayBacked(
            PreApply.class,
            listeners -> context -> {
                for (PreApply listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.preApply(context), "PlayKnockback.PRE_APPLY");
                }
            }
    );

    public static final Event<ModifyForce> MODIFY_FORCE = EventFactory.createArrayBacked(
            ModifyForce.class,
            listeners -> context -> {
                for (ModifyForce listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.modifyForce(context), "PlayKnockback.MODIFY_FORCE");
                }
            }
    );
}
