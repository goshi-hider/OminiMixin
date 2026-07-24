package dev.goshi.omnimixin.api.common;

public final class MutableInt {

    private int value;

    public MutableInt(int value) {
        this.value = value;
    }

    public int get() {
        return this.value;
    }

    public void set(int value) {
        this.value = value;
    }

    public void add(int amount) {
        this.value += amount;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
