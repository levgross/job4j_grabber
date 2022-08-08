package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    private final static ActionDelegate STUB_ACTION = System.out::println;
    private final static String ACTION_CHOICE = """
        Choose an action:
        1. Add menu item
        2. Output menu to console
        3. Exit
        """;
    private final static int ADD = 1;
    private final static int PRINT = 2;
    private final static int EXIT = 3;
    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsolePrinter();
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println(ACTION_CHOICE);
            int num = Integer.parseInt(input.nextLine());
            switch (num) {
                case(ADD):
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
                case(PRINT):
                    printer.print(menu);
                    System.out.println();
                    break;
                case(EXIT):
                    run = false;
                    break;
                default:
                    System.out.println("Wrong number!");
                    break;
            }
        }
    }
}
