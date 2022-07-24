package ru.job4j.ood.lsp;

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
                LocalDate.of(2022, 7, 29),
                LocalDate.of(2022, 7, 24),
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
                LocalDate.of(2022, 7, 28),
                LocalDate.of(2022, 7, 24),
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
                LocalDate.of(2022, 7, 28),
                LocalDate.of(2022, 6, 24),
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
                LocalDate.of(2022, 7, 24),
                LocalDate.of(2022, 6, 24),
                100,
                0.3d);
        controlQuality.control(testFood);
        assertThat(warehouse.getAll().size()).isEqualTo(0);
        assertThat(shop.getAll().size()).isEqualTo(0);
        assertThat(trash.getAll().size()).isEqualTo(1);
    }
}