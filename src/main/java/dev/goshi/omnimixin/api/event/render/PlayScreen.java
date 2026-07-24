package dev.goshi.omnimixin.api.event.render;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public final class PlayScreen {

    private PlayScreen() {
    }

    @FunctionalInterface
    public interface Open {
        void open(ScreenOpenContext context);
    }

    @FunctionalInterface
    public interface Init {
        void init(ScreenContext context);
    }

    @FunctionalInterface
    public interface Close {
        void close(ScreenContext context);
    }

    public static final Event<Open> OPEN = EventFactory.createArrayBacked(
            Open.class,
            listeners -> context -> {
                for (Open listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.open(context), "PlayScreen.OPEN");
                }
            }
    );

    public static final Event<Init> INIT = EventFactory.createArrayBacked(
            Init.class,
            listeners -> context -> {
                for (Init listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.init(context), "PlayScreen.INIT");
                }
            }
    );

    public static final Event<Close> CLOSE = EventFactory.createArrayBacked(
            Close.class,
            listeners -> context -> {
                for (Close listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.close(context), "PlayScreen.CLOSE");
                }
            }
    );
}
