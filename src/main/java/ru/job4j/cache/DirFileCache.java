package ru.job4j.cache;

import java.io.*;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(key))) {
           for (String line = in.readLine(); line != null; line = in.readLine()) {
               text.append(line + System.lineSeparator());
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
