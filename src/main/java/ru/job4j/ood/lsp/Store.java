package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static java.time.temporal.ChronoUnit.DAYS;

public interface Store {
    void add(Food food);
    boolean assign(Food food);
    List<Food> getAll();
}
