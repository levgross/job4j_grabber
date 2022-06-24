package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AbstractCache cache = new DirFileCache(".\\");
        String file;
        boolean run = true;
        while (run) {
            System.out.println("Enter file name:");
            file = scanner.nextLine();
            System.out.println("Choose an action:");
            System.out.println("1. Print the data from cache.");
            System.out.println("2. Exit.");
            int select = Integer.parseInt(scanner.nextLine());
            if (select == 1) {
                System.out.println(cache.get(file));
            } else if (select == 2) {
                run = false;
            } else {
                System.out.println("Wrong chose! Input the correct number of an action");
            }
        }
    }
}
