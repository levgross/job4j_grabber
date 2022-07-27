package ru.job4j.ood.lsp.parking;

public class Track implements Auto {
    int size;
    private boolean isParked = false;

    public Track(int size) {
        this.size = size;
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
