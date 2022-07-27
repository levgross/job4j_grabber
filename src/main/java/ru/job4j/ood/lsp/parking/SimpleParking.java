package ru.job4j.ood.lsp.parking;

public class SimpleParking implements Parking {
    int carsCount;
    int tracksCount;


    public SimpleParking(int carsCount, int tracksCount) {
        this.carsCount = carsCount;
        this.tracksCount = tracksCount;
    }

    public int getCarsCount() {
        return carsCount;
    }

    public int getTracksCount() {
        return tracksCount;
    }

    @Override
    public boolean park(Auto auto) {
        return false;
    }
}
