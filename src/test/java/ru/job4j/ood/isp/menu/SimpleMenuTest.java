package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

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
        assertThat(menu.add("Visit shop", "Buy bread", STUB_ACTION))
                .isFalse();
    }

    @Test
    public void whenConsolePrint() {
        var menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Visit shop", STUB_ACTION);
        menu.add(Menu.ROOT, "Feed the dog", STUB_ACTION);
        menu.add("Visit shop", "Buy food", STUB_ACTION);
        menu.add("Buy food", "Buy bread", STUB_ACTION);
        menu.add("Buy food", "Buy milk", STUB_ACTION);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new ConsolePrinter().print(menu);
        assertThat(outContent.toString()).isEqualTo("""
                1. Visit shop\r
                ---1.1. Buy food\r
                ------1.1.1. Buy bread\r
                ------1.1.2. Buy milk\r
                2. Feed the dog\r
                """);
        System.setOut(originalOut);
    }
}