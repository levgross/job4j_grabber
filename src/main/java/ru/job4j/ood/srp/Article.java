package ru.job4j.ood.srp;

import java.util.List;

public interface Article<T> {
    T create(List<String> words);
    void save(T article);
}
