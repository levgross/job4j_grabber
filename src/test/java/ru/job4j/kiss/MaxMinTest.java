package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MaxMinTest {
    List<String> strings = new ArrayList<>();
    List<Integer> numbers = new ArrayList<>();

    @Before
    public void initData() {
        strings.add("an");
        strings.add("two");
        strings.add("three");
        strings.add("four");
        strings.add(null);
        strings.add("five");

        numbers.add(1);
        numbers.add(5);
        numbers.add(2);
        numbers.add(4);
        numbers.add(3);
    }

    @Test
    public void whenMaxForIntegers() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        var value = MaxMin.max(numbers, comparator);
        Assert.assertEquals(numbers.get(1), value);
    }

    @Test
    public void whenMinForIntegers() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        var value = MaxMin.min(numbers, comparator);
        Assert.assertEquals(numbers.get(0), value);
    }

    @Test
    public void whenMaxForStrings() {
        Comparator<String> comparator = Comparator.comparing(String::length);
        var value = MaxMin.max(strings, comparator);
        Assert.assertEquals("three", value);
    }

    @Test
    public void whenMinForStrings() {
        Comparator<String> comparator = Comparator.comparing(String::length);
        var value = MaxMin.min(strings, comparator);
        Assert.assertEquals("an", value);
    }
}