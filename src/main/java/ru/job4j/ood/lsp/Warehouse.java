package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private List<Food> foods = new ArrayList<>();
    private final static int BOUND = 25;

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (assign(food)) {
            foods.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public boolean assign(Food food) {
        return check(food) < BOUND;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(foods);
    }
}
