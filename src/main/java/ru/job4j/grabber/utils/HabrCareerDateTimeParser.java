package ru.job4j.grabber.utils;

import java.time.ZonedDateTime;

public class HabrCareerDateTimeParser implements DateTimeParser {
    @Override
    public ZonedDateTime parse(String parse) {
        return ZonedDateTime.parse(parse);
    }
}
