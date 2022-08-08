package ru.job4j.ood.isp.menu;

public class ConsolePrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
            String num = i.getNumber();
            for (int j = 1; j < num.split("\\.").length; j++) {
                System.out.print("---");
            }
            System.out.println(num + " " + i.getName());
        });
    }
}
