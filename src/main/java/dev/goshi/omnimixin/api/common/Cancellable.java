package dev.goshi.omnimixin.api.common;

public final class Cancellable {

    private boolean cancelled = false;

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
