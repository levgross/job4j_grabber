package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Disabled
    @Test
    public void whenAddThenReturnSame() {
        var menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Visit shop", STUB_ACTION);
        menu.add(Menu.ROOT, "Feed the dog", STUB_ACTION);
        menu.add("Visit shop", "Buy food", STUB_ACTION);
        menu.add("Buy food", "Buy bread", STUB_ACTION);
        menu.add("Buy food", "Buy milk", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo(
                "Visit shop", List.of("Buy food"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Visit shop").get()
                );
        assertThat(
                new Menu.MenuItemInfo(
                        "Buy food", List.of("Buy bread", "Buy milk"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Buy food").get()
                );
        assertThat(
                new Menu.MenuItemInfo(
                        "Feed the dog", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Feed the dog").get()
                );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }
}