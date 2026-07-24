package dev.goshi.omnimixin.api.event.movement;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayJump {

    private PlayJump() {
    }

    @FunctionalInterface
    public interface PreJump {
        void preJump(JumpContext context);
    }

    @FunctionalInterface
    public interface ModifyImpulse {
        void modifyImpulse(JumpContext context);
    }

    @FunctionalInterface
    public interface PostJump {
        void postJump(JumpContext context);
    }

    public static final Event<PreJump> PRE_JUMP = EventFactory.createArrayBacked(
            PreJump.class,
            listeners -> context -> {
                for (PreJump listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.preJump(context), "PlayJump.PRE_JUMP");
                }
            }
    );

    public static final Event<ModifyImpulse> MODIFY_IMPULSE = EventFactory.createArrayBacked(
            ModifyImpulse.class,
            listeners -> context -> {
                for (ModifyImpulse listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.modifyImpulse(context), "PlayJump.MODIFY_IMPULSE");
                }
            }
    );

    public static final Event<PostJump> POST_JUMP = EventFactory.createArrayBacked(
            PostJump.class,
            listeners -> context -> {
                for (PostJump listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.postJump(context), "PlayJump.POST_JUMP");
                }
            }
    );
}
