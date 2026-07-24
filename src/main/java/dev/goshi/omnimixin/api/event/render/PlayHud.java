package dev.goshi.omnimixin.api.event.render;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public final class PlayHud {

    private PlayHud() {
    }

    @FunctionalInterface
    public interface HudRender {
        void render(HudContext context);
    }

    public static final Event<HudRender> PRE_HUD = EventFactory.createArrayBacked(
            HudRender.class,
            listeners -> context -> {
                for (HudRender listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.render(context), "PlayHud.PRE_HUD");
                }
            }
    );

    public static final Event<HudRender> POST_HUD = EventFactory.createArrayBacked(
            HudRender.class,
            listeners -> context -> {
                for (HudRender listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.render(context), "PlayHud.POST_HUD");
                }
            }
    );
}
