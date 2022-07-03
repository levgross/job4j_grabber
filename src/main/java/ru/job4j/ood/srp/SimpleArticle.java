package ru.job4j.ood.srp;

import java.util.List;

public class SimpleArticle implements Article<StringBuilder> {
    @Override
    public StringBuilder create(List<String> words) {
        StringBuilder article = new StringBuilder();
        words.stream()
                .map((x) -> x + " ")
                .forEach(article::append);
        return article;
    }

    @Override
    public void save(StringBuilder article) {

    }
}
