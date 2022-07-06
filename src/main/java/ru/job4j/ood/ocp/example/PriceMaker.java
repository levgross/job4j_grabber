package ru.job4j.ood.ocp.example;

public class PriceMaker {
    private static final double MARKUP = 0.15d;
    private static final double LOGISTIC_MARKUP = 0.05d;

    public double price(double cost) {
        return cost * (1 + MARKUP);
    }

    public double priceWithLogistic(double cost) {
        return cost * (1 + LOGISTIC_MARKUP) * (1 + MARKUP);
    }
}
