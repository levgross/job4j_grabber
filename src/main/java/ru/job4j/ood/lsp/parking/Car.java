package ru.job4j.ood.lsp.parking;

public class Car implements Auto {
    public static final int CAR_SIZE = 1;

    public Car() {
    }

    @Override
    public int getSize() {
        return CAR_SIZE;
    }
}
