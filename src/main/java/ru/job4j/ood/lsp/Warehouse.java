package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private List<Food> foods = new ArrayList<>();
    private final static int BOUND = 25;

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public boolean assign(Food food) {
        boolean result = false;
        if (ControlQuality.check(food) < BOUND) {
            foods.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> getAll() {
        return foods;
    }
}
