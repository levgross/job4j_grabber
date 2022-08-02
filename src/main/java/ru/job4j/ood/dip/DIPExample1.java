package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is created to demonstrate a violation of DIP.
 * @author Grossevich Lev
 */
public class DIPExample1 {
    /**
     * Our class depends on storage realization - HashMap. We won`t be able to store our users in database
     * for example without changing our class.
     * We should create new interface UserStore and add dependency on this abstraction to our class.
     */
    private Map<Integer, User> users = new HashMap<>();

    public boolean addUser(User user) {
       return users.put(user.id, user) != null;
    }

    class User {
        private int id;
        private String name;
        private int age;

        public User(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }
}
