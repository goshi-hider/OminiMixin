package dev.goshi.omnimixin.api.common;

public final class MutableFloat {

    private float value;

    public MutableFloat(float value) {
        this.value = value;
    }

    public float get() {
        return this.value;
    }

    public void set(float value) {
        this.value = value;
    }

    public void add(float amount) {
        this.value += amount;
    }

    public void multiply(float factor) {
        this.value *= factor;
    }

    @Override
    public String toString() {
        return Float.toString(this.value);
    }
}
