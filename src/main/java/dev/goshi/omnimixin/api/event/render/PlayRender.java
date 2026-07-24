package dev.goshi.omnimixin.api.event.render;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public final class PlayRender {

    private PlayRender() {
    }

    @FunctionalInterface
    public interface EntityRender {
        void render(RenderContext context);
    }

    public static final Event<EntityRender> PRE_ENTITY = EventFactory.createArrayBacked(
            EntityRender.class,
            listeners -> context -> {
                for (EntityRender listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.render(context), "PlayRender.PRE_ENTITY");
                }
            }
    );

    public static final Event<EntityRender> POST_ENTITY = EventFactory.createArrayBacked(
            EntityRender.class,
            listeners -> context -> {
                for (EntityRender listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.render(context), "PlayRender.POST_ENTITY");
                }
            }
    );
}
