package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name:");
        String file = scanner.nextLine();
        AbstractCache cache = new DirFileCache(file);
        boolean run = true;
        while (run) {
            System.out.println("Choose an action:");
            System.out.println("1. Load the data from file to cache.");
            System.out.println("2. Print the data from cache.");
            int select = Integer.parseInt(scanner.nextLine());
            if (select == 1) {
                cache.put(file, cache.load(file));
                System.out.println("OK");
            } else if (select == 2) {
                System.out.println(cache.get(file));
            } else {
                System.out.println("Wrong chose! Input the correct number of an action");
                continue;
            }
            run = false;
        }
    }
}
