package ru.job4j.ood.lsp.parking;

public class Track implements Auto {
    private final int size;

    public Track(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Track`s size should be more then 1.");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
