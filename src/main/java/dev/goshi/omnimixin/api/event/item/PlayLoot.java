package dev.goshi.omnimixin.api.event.item;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayLoot {

    private PlayLoot() {
    }

    @FunctionalInterface
    public interface ModifyDrops {
        void modify(BlockLootContext context);
    }

    public static final Event<ModifyDrops> MODIFY_DROPS = EventFactory.createArrayBacked(
            ModifyDrops.class,
            listeners -> context -> {
                for (ModifyDrops listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.modify(context), "PlayLoot.MODIFY_DROPS");
                }
            }
    );
}
