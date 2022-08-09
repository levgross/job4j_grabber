package ru.job4j.ood.lsp.foodcontrol;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;
    private List<Food> foods = new ArrayList<>();

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(Food food) {
        for (Store store : stores) {
            if (store.add(food)) {
                break;
            }
        }
    }

    public void resort() {
        for (Store store : stores) {
            foods.addAll(store.getAll());
            store.clear();
        }
        foods.forEach(this::control);
    }
}
