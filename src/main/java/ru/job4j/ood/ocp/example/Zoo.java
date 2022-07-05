package ru.job4j.ood.ocp.example;

import java.util.List;

public class Zoo {

    public static class Animal {
        public void eat() {
            System.out.println("Ест мясо.");
        }
    }

    public static class Tiger extends Animal {
    }

    public static class Wolf extends Animal {
    }

    public static void main(String[] args) {
      List<Animal> animals = List.of(new Tiger(), new Wolf());
      animals.forEach(Animal::eat);
    }
}
