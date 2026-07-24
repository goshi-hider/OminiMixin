package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayDamage {

    private PlayDamage() {
    }

    @FunctionalInterface
    public interface PreCalculate {
        void preCalculate(DamageContext context);
    }

    @FunctionalInterface
    public interface ModifyAmount {
        void modifyAmount(DamageContext context);
    }

    @FunctionalInterface
    public interface PostApply {
        void postApply(DamageContext context, boolean damageWasApplied);
    }

    public static final Event<PreCalculate> PRE_CALCULATE = EventFactory.createArrayBacked(
            PreCalculate.class,
            listeners -> context -> {
                for (PreCalculate listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.preCalculate(context), "PlayDamage.PRE_CALCULATE");
                }
            }
    );

    public static final Event<ModifyAmount> MODIFY_AMOUNT = EventFactory.createArrayBacked(
            ModifyAmount.class,
            listeners -> context -> {
                for (ModifyAmount listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.modifyAmount(context), "PlayDamage.MODIFY_AMOUNT");
                }
            }
    );

    public static final Event<PostApply> POST_APPLY = EventFactory.createArrayBacked(
            PostApply.class,
            listeners -> (context, applied) -> {
                for (PostApply listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.postApply(context, applied), "PlayDamage.POST_APPLY");
                }
            }
    );
}
