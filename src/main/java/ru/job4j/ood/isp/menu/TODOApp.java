package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        final ActionDelegate STUB_ACTION = System.out::println;
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("""
        Choose an action:
        1. Add menu item
        2. Output menu to console
        3. Exit
        """);
            int num = Integer.parseInt(input.nextLine());
            switch (num) {
                case(1):
                    System.out.println("Enter menu item");
                    String name = input.nextLine();
                    System.out.println("Enter name of the parent item. If it doesn`t have one? enter no");
                    String parentName = input.nextLine();
                    if ("no".equals(parentName)) {
                        parentName = Menu.ROOT;
                    }
                    if (menu.add(parentName, name, STUB_ACTION)) {
                        System.out.println("New item is added");
                    } else {
                        System.out.println("New item is not added");
                    }
                    break;
                case(2):
                    new ConsolePrinter().print(menu);
                    System.out.println("------------");
                    break;
                case(3):
                    run = false;
                    break;
                default:
                    System.out.println("Wrong number!");
                    break;
            }
        }
    }
}
