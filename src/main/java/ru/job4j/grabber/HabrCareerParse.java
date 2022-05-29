package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);
    private final DateTimeParser dateTimeParser;
    public static final int PAGES = 5;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        try {
            for (int i = 1; i <= PAGES; i++) {
                Connection connection = Jsoup.connect(String.format("%s%s", link, i));
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                rows.forEach(row -> {
                    posts.add(parseElement(row));
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            return posts;
    }

    private Post parseElement(Element element) {
        Element titleElement = element.select(".vacancy-card__title").first();
        Element linkElement = titleElement.child(0);
        Element dateElement = element.select(".vacancy-card__date").first();
        String vacancyName = titleElement.text();
        String url = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
        String date = dateElement.child(0).attr("datetime");
        return new Post(vacancyName, url, retrieveDescription(url), dateTimeParser.parse(date));
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
