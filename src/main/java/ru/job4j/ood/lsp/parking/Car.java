package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Car implements Auto {
    public static final int CAR_SIZE = 1;
    private final int number = NumberCounter.nextNumber();

    public Car() {
    }

    @Override
    public int getSize() {
        return CAR_SIZE;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return number == car.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Car{"
                + "number=" + number
                + '}';
    }
}
