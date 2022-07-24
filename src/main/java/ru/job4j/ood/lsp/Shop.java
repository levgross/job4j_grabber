package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private List<Food> foods = new ArrayList<>();
    private final static int LOWER_BOUND = 25;
    private final static int MIDDLE_BOUND = 75;
    private final static int UPPER_BOUND = 100;

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public boolean assign(Food food) {
        boolean result = false;
        double checker = ControlQuality.check(food);
        if (checker >= LOWER_BOUND && checker <= MIDDLE_BOUND) {
            foods.add(food);
            result = true;
        }
        if (checker > MIDDLE_BOUND && checker < UPPER_BOUND) {
            food.setPrice(discountedPrice(food));
            foods.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> getAll() {
        return foods;
    }

    private double discountedPrice(Food food) {
        return food.getPrice() * (1 - food.getDiscount());
    }
}
