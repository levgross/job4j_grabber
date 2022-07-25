package ru.job4j.ood.lsp.foodcontrol;

import java.time.LocalDate;

public class Eggs extends Food {
    public Eggs(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
