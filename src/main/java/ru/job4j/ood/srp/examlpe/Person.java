package ru.job4j.ood.srp.examlpe;

public class Person {
    private String name;
    private int age;
    private boolean isMarried;
    private int children;
    private String passport;

    public void printPerson(Person person) {
        System.out.println(person.toString());
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", isMarried=" + isMarried
                + ", children=" + children
                + ", passport='" + passport + '\''
                + '}';
    }
}
