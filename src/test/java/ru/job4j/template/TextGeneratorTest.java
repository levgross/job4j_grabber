package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TextGeneratorTest {

    @Ignore
    @Test
    public void whenProduceText() {
        Generator tg = new TextGenerator();
        String template = "I am ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String expected = "I am Petr Arsentev, Who are you? ";
        assertEquals(expected, tg.produce(template, args));
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenNoSuchArgThenException() {
        Generator tg = new TextGenerator();
        String template = "I am ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        tg.produce(template, args);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void whenArgNotUsedInTemplateThenException() {
        Generator tg = new TextGenerator();
        String template = "I am ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("test", "test");
        tg.produce(template, args);
    }
}