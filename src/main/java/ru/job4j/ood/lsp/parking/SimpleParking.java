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
        int autoSize = auto.getSize();
        boolean result = true;

        if (auto.isParked()) {
            return false;
        }

        if (autoSize == 1 && carsCount > 0) {
            auto.setParked(true);
            carsCount--;
        } else if (autoSize > 1 && tracksCount > 0) {
            auto.setParked(true);
            tracksCount--;
        } else if (autoSize > 1 && carsCount >= autoSize) {
            auto.setParked(true);
            carsCount -= autoSize;
        } else {
            result = false;
        }

        return result;
    }
}
