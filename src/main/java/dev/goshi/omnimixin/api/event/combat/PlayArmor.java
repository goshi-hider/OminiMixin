package dev.goshi.omnimixin.api.event.combat;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayArmor {

    private PlayArmor() {
    }

    @FunctionalInterface
    public interface ModifyProtection {
        void modify(ArmorContext context);
    }

    @FunctionalInterface
    public interface OnDurabilityDamage {
        void onDurabilityDamage(DurabilityContext context);
    }

    public static final Event<ModifyProtection> MODIFY_PROTECTION = EventFactory.createArrayBacked(
            ModifyProtection.class,
            listeners -> context -> {
                for (ModifyProtection listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.modify(context), "PlayArmor.MODIFY_PROTECTION");
                }
            }
    );

    public static final Event<OnDurabilityDamage> ON_DURABILITY_DAMAGE = EventFactory.createArrayBacked(
            OnDurabilityDamage.class,
            listeners -> context -> {
                for (OnDurabilityDamage listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.onDurabilityDamage(context), "PlayArmor.ON_DURABILITY_DAMAGE");
                }
            }
    );
}
