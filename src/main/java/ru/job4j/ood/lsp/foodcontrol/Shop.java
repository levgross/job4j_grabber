package ru.job4j.ood.lsp.foodcontrol;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private List<Food> foods = new ArrayList<>();
    private final static int LOWER_BOUND = 25;
    private final static int MIDDLE_BOUND = 75;
    private final static int UPPER_BOUND = 100;


    @Override
    public boolean add(Food food) {
        boolean result = false;
        double checker = check(food);
        if (assign(food)) {
            if (checker > MIDDLE_BOUND && checker < UPPER_BOUND && !food.isDiscounted()) {
                food.setPrice(discountedPrice(food));
            }
            foods.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public boolean assign(Food food) {
        double checker = check(food);
        return checker >= LOWER_BOUND && checker < UPPER_BOUND;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(foods);
    }

    @Override
    public List<Food> clear() {
        List<Food> copyFoods = foods;
        foods.clear();
        return copyFoods;
    }

    private double discountedPrice(Food food) {
        food.setDiscounted(true);
        return food.getPrice() * (1 - food.getDiscount());
    }

}
