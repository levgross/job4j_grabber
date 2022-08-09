package ru.job4j.ood.lsp.foodcontrol;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ControlQualityTest {

    @Test
    void whenWarehouseIsUsed() {
        List<Store> stores = new ArrayList<>(3);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.control(new Apple("GreenApple",
                LocalDate.now().plusDays(4),
                LocalDate.now().minusDays(1),
                100,
                0.3d));
        assertThat(warehouse.getAll().size()).isEqualTo(1);
        assertThat(shop.getAll().size()).isEqualTo(0);
        assertThat(trash.getAll().size()).isEqualTo(0);
    }

    @Test
    void whenShopIsUsed() {
        List<Store> stores = new ArrayList<>(3);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food testFood = new Apple("GreenApple",
                LocalDate.now().plusDays(3),
                LocalDate.now().minusDays(1),
                100,
                0.3d);
        controlQuality.control(testFood);
        assertThat(warehouse.getAll().size()).isEqualTo(0);
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(trash.getAll().size()).isEqualTo(0);
    }

    @Test
    void whenShopIsUsedAndDiscounted() {
        List<Store> stores = new ArrayList<>(3);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food testFood = new Apple("GreenApple",
                LocalDate.now().plusDays(3),
                LocalDate.now().minusDays(30),
                100,
                0.3d);
        controlQuality.control(testFood);
        assertThat(warehouse.getAll().size()).isEqualTo(0);
        assertThat(shop.getAll().size()).isEqualTo(1);
        assertThat(trash.getAll().size()).isEqualTo(0);
        assertThat(testFood.getPrice()).isEqualTo(100 * (1 - 0.3));
    }

    @Test
    void whenTrashIsUsed() {
        List<Store> stores = new ArrayList<>(3);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food testFood = new Apple("GreenApple",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(30),
                100,
                0.3d);
        controlQuality.control(testFood);
        assertThat(warehouse.getAll().size()).isEqualTo(0);
        assertThat(shop.getAll().size()).isEqualTo(0);
        assertThat(trash.getAll().size()).isEqualTo(1);
    }

    @Test
    void whenAllStoresUsed() {
        List<Store> stores = new ArrayList<>(3);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food greenApple = new Apple("GreenApple",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(30),
                100,
                0.3d);
        controlQuality.control(greenApple);
        Food milk = new Milk("Milk 1l.",
                LocalDate.now().plusDays(13),
                LocalDate.now().minusDays(1),
                100,
                0.3d);
        controlQuality.control(milk);
        Food eggs = new Eggs("Eggs 30 items",
                LocalDate.now().plusDays(5),
                LocalDate.now().minusDays(5),
                100,
                0.3d);
        controlQuality.control(eggs);
        Food eggs1 = new Eggs("Eggs 10 items",
                LocalDate.now().plusDays(3),
                LocalDate.now().minusDays(30),
                100,
                0.3d);
        controlQuality.control(eggs1);
        assertThat(warehouse.getAll().size()).isEqualTo(1);
        assertThat(shop.getAll().size()).isEqualTo(2);
        assertThat(trash.getAll().size()).isEqualTo(1);
        assertThat(eggs.getPrice()).isEqualTo(100);
        assertThat(eggs1.getPrice()).isEqualTo(100 * (1 - eggs1.getDiscount()));
    }

    @Test
    public void whenResort() {
        List<Store> stores = new ArrayList<>(3);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        Food greenApple = new Apple("GreenApple",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(30),
                100,
                0.3d);
        controlQuality.control(greenApple);
        Food milk = new Milk("Milk 1l.",
                LocalDate.now().plusDays(13),
                LocalDate.now().minusDays(1),
                100,
                0.3d);
        controlQuality.control(milk);
        Food eggs = new Eggs("Eggs 30 items",
                LocalDate.now().plusDays(5),
                LocalDate.now().minusDays(5),
                100,
                0.3d);
        controlQuality.control(eggs);
        Food eggs1 = new Eggs("Eggs 10 items",
                LocalDate.now().plusDays(3),
                LocalDate.now().minusDays(30),
                100,
                0.3d);
        controlQuality.control(eggs1);
        controlQuality.resort();
        assertThat(warehouse.getAll().size()).isEqualTo(1);
        assertThat(shop.getAll().size()).isEqualTo(2);
        assertThat(trash.getAll().size()).isEqualTo(1);
        assertThat(eggs.getPrice()).isEqualTo(100);
        assertThat(eggs1.getPrice()).isEqualTo(100 * (1 - eggs1.getDiscount()));
    }
}
