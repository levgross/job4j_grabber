package ru.job4j.ood.lsp.parking;

public class Track implements Auto {
    int size;

    public Track(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return 0;
    }
}