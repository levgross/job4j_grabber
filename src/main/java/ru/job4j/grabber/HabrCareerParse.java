package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class HabrCareerParse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);
    private static int pages = 5;

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= pages; i++) {
            Connection connection = Jsoup.connect(String.format("%s%s", PAGE_LINK, i));
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                Element dateElement = row.select(".vacancy-card__date").first();
                String vacancyName = titleElement.text();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String date = dateElement.child(0).attr("datetime");
                System.out.printf("%s %s %s%n", vacancyName, link, date);
            });
        }
    }

    private String retrieveDescription(String link) {
        StringBuilder description = new StringBuilder();
        try {
            Connection connection = Jsoup.connect(link);
            Document document = connection.get();
            Elements descElements = document.select(".job_show_description__vacancy_description");
            descElements.forEach(desc -> {
                description.append(String.format("%s%n", desc.text()));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return description.toString();
    }
}
