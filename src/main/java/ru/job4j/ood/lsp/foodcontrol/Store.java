package ru.job4j.ood.lsp.foodcontrol;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public interface Store {
    boolean add(Food food);
    boolean assign(Food food);
    List<Food> getAll();

    default double check(Food food) {
        double daysLeft = (double) DAYS.between(LocalDate.now(), food.getExpiryDate());
        double allPeriod = (double) DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return  (1 - daysLeft / allPeriod) * 100;
    }
}
