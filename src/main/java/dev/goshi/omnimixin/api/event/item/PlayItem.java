package dev.goshi.omnimixin.api.event.item;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayItem {

    private PlayItem() {
    }

    @FunctionalInterface
    public interface Use {
        void use(UseContext context);
    }

    @FunctionalInterface
    public interface UseOnBlock {
        void useOnBlock(UseOnBlockContext context);
    }

    @FunctionalInterface
    public interface FinishUse {
        void finishUse(FinishUseContext context);
    }

    public static final Event<Use> USE = EventFactory.createArrayBacked(
            Use.class,
            listeners -> context -> {
                for (Use listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.use(context), "PlayItem.USE");
                }
            }
    );

    public static final Event<UseOnBlock> USE_ON_BLOCK = EventFactory.createArrayBacked(
            UseOnBlock.class,
            listeners -> context -> {
                for (UseOnBlock listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.useOnBlock(context), "PlayItem.USE_ON_BLOCK");
                }
            }
    );

    public static final Event<FinishUse> FINISH_USE = EventFactory.createArrayBacked(
            FinishUse.class,
            listeners -> context -> {
                for (FinishUse listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.finishUse(context), "PlayItem.FINISH_USE");
                }
            }
    );
}
