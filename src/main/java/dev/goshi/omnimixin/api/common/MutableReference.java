package dev.goshi.omnimixin.api.common;

public final class MutableReference<T> {

    private T value;

    public MutableReference(T value) {
        this.value = value;
    }

    public T get() {
        return this.value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
