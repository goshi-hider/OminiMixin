package dev.goshi.omnimixin.api.common;

public final class MutableDouble {

    private double value;

    public MutableDouble(double value) {
        this.value = value;
    }

    public double get() {
        return this.value;
    }

    public void set(double value) {
        this.value = value;
    }

    public void add(double amount) {
        this.value += amount;
    }

    public void multiply(double factor) {
        this.value *= factor;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }
}
