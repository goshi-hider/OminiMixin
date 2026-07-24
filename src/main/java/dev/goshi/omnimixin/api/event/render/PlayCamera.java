package dev.goshi.omnimixin.api.event.render;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public final class PlayCamera {

    private PlayCamera() {
    }

    @FunctionalInterface
    public interface CalculateFov {
        void calculate(FovContext context);
    }

    public static final Event<CalculateFov> CALCULATE_FOV = EventFactory.createArrayBacked(
            CalculateFov.class,
            listeners -> context -> {
                for (CalculateFov listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.calculate(context), "PlayCamera.CALCULATE_FOV");
                }
            }
    );
}
