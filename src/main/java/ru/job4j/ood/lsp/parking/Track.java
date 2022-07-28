package ru.job4j.ood.lsp.parking;

import java.util.Objects;

import static ru.job4j.ood.lsp.parking.Car.CAR_SIZE;

public class Track implements Auto {
    private final int size;
    private final int number = NumberCounter.nextNumber();

    public Track(int size) {
        if (size <= CAR_SIZE) {
            throw new IllegalArgumentException("Track`s size is wrong!");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
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
        Track track = (Track) o;
        return number == track.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Track{"
                + "number=" + number
                + '}';
    }
}
