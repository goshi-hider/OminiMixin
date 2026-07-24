package dev.goshi.omnimixin.api.event.entity;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlaySpawn {

    private PlaySpawn() {
    }

    @FunctionalInterface
    public interface CheckRules {

        boolean checkRules(SpawnRulesContext context);
    }

    @FunctionalInterface
    public interface PreSpawn {
        void preSpawn(SpawnContext context);
    }

    @FunctionalInterface
    public interface PostSpawn {
        void postSpawn(SpawnContext context, boolean spawned);
    }

    public static final Event<CheckRules> CHECK_RULES = EventFactory.createArrayBacked(
            CheckRules.class,
            listeners -> context -> {
                for (CheckRules listener : listeners) {
                    boolean[] allowed = {true};
                    OmniMixinLog.safeRun(() -> allowed[0] = listener.checkRules(context), "PlaySpawn.CHECK_RULES");
                    if (!allowed[0]) {
                        return false;
                    }
                }
                return true;
            }
    );

    public static final Event<PreSpawn> PRE_SPAWN = EventFactory.createArrayBacked(
            PreSpawn.class,
            listeners -> context -> {
                for (PreSpawn listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.preSpawn(context), "PlaySpawn.PRE_SPAWN");
                }
            }
    );

    public static final Event<PostSpawn> POST_SPAWN = EventFactory.createArrayBacked(
            PostSpawn.class,
            listeners -> (context, spawned) -> {
                for (PostSpawn listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.postSpawn(context, spawned), "PlaySpawn.POST_SPAWN");
                }
            }
    );
}
