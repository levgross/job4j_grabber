package ru.job4j.ood.lsp.parking;

public class SimpleParking implements Parking {
    int cars;
    int tracks;

    public SimpleParking(int cars, int tracks) {
        this.cars = cars;
        this.tracks = tracks;
    }

    public int getCars() {
        return cars;
    }

    public int getTracks() {
        return tracks;
    }

    @Override
    public boolean park(Auto auto) {
        return false;
    }
}
