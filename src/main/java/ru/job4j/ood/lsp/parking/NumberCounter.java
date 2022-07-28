package ru.job4j.ood.lsp.parking;

public class NumberCounter {
    private static int number = 0;

    public static int nextNumber() {
       return ++number;
    }
}
