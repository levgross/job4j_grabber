package ru.job4j.ood.lsp.parking;

public class Car implements Auto {
    private final int size = 1;
    private boolean isParked = false;

    public Car() {
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isParked() {
        return isParked;
    }

    @Override
    public void setParked(boolean parked) {
        isParked = parked;
    }
}
