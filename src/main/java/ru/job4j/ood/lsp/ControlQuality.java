package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(Food food) {
        for (Store store : stores) {
            if (store.assign(food)) {
                break;
            }
        }
    }

    public static double check(Food food) {
        double daysLeft = (double) DAYS.between(LocalDate.now(), food.getExpiryDate());
        double allPeriod = (double) DAYS.between(food.getCreateDate(), food.getExpiryDate());

        return  (1 - daysLeft / allPeriod) * 100;
    }

    public static void main(String[] args) {
        List<Store> stores = new ArrayList<>(3);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food greenApple = new Apple("GreenApple",
        LocalDate.of(2022, 7, 29),
        LocalDate.of(2022, 6, 24),
        100,
        0.3d);
        controlQuality.control(greenApple);
        System.out.println(check(greenApple));
    }
}
