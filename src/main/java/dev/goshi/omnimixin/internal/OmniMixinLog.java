package dev.goshi.omnimixin.internal;

import dev.goshi.OmniMixin;

public final class OmniMixinLog {

    private OmniMixinLog() {
    }

    @FunctionalInterface
    public interface UnsafeRunnable {
        void run() throws Throwable;
    }

    public static void safeRun(UnsafeRunnable action, String hookName) {
        try {
            action.run();
        } catch (Throwable t) {
            OmniMixin.LOGGER.error(
                    "[OmniMixin] A listener registered on '{}' threw an exception. "
                            + "This is almost certainly a bug in a mod that uses OmniMixin, not in OmniMixin "
                            + "itself or in the game - the listener has been skipped and play continues.",
                    hookName, t
            );
        }
    }
}
