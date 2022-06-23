package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name:");
        String file = scanner.nextLine();
        AbstractCache cache = new DirFileCache(file);
        System.out.println(cache.get(file));
    }
}
