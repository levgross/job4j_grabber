package ru.job4j.ood.lsp.parking;

import java.util.HashSet;
import java.util.Set;

import static ru.job4j.ood.lsp.parking.Car.CAR_SIZE;

public class SimpleParking implements Parking {
    private int carsCount;
    private int tracksCount;
    private Set<Auto> carsParked;
    private Set<Auto> tracksParked;

    public SimpleParking(int carsCount, int tracksCount) {
        this.carsCount = carsCount;
        this.tracksCount = tracksCount;
        carsParked = new HashSet<>(carsCount);
        tracksParked = new HashSet<>(tracksCount);
    }

    public int getCarsCount() {
        return carsCount;
    }

    public int getTracksCount() {
        return tracksCount;
    }

    public Set<Auto> getCarsParked() {
        return new HashSet<>(carsParked);
    }

    public Set<Auto> getTracksParked() {
        return new HashSet<>(tracksParked);
    }

    @Override
    public boolean park(Auto auto) {
        int autoSize = auto.getSize();
        boolean result = true;

        if (autoSize == CAR_SIZE && carsCount > 0 && carsParked.add(auto)) {
            carsCount--;
        } else if (autoSize > CAR_SIZE && tracksCount > 0 && tracksParked.add(auto)) {
            tracksCount--;
        } else if (autoSize > CAR_SIZE && carsCount >= autoSize && carsParked.add(auto)) {
            carsCount -= autoSize;
        } else {
            result = false;
        }

        return result;
    }
}
