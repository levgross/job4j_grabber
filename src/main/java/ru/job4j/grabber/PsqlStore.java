package ru.job4j.grabber;

import ru.job4j.quartz.AlertRabbit;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cnn.prepareStatement(
                "insert into post(name, text, link, created) values(?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getLink());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            ps.execute();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement("select * from post")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    posts.add(new Post(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("link"),
                            rs.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement ps = cnn.prepareStatement("select * from post where id = ?")) {
           ps.setInt(1, id);
           try (ResultSet rs = ps.executeQuery()) {
               if (rs.next()) {
               post = new Post(
                       rs.getInt("id"),
                       rs.getString("name"),
                       rs.getString("text"),
                       rs.getString("link"),
                       rs.getTimestamp("created").toLocalDateTime()
               );
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) {
        Properties config = AlertRabbit.config();
        PsqlStore psqlStore = new PsqlStore(config);
        psqlStore.save(new Post(
                "вакансия_1",
                "ссылка_1",
                "описание_1",
                LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(System.currentTimeMillis()),
                        TimeZone.getDefault().toZoneId()))
        );
        psqlStore.save(new Post(
                "вакансия_2",
                "ссылка_2",
                "описание_2",
                LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(System.currentTimeMillis()),
                        TimeZone.getDefault().toZoneId()))
        );
        System.out.println(psqlStore.findById(1));
        System.out.println();
        List<Post> posts = psqlStore.getAll();
        posts.forEach(System.out :: println);
    }
}
