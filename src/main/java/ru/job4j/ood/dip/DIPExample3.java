package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class is created to demonstrate a violation of DIP.
 * @author Grossevich Lev
 */
public class DIPExample3 {
    private UserStore userStore;

    public DIPExample3(UserStore userStore) {
        this.userStore = userStore;
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

    class Group {
        private String title;
        /**
         * Our class depends on storage realization - HashSet. We won`t be able to store our users in database
         * for example without changing our class.
         * We should create new interface GroupStore and add dependency on this abstraction to our executable class.
         */
        Set<User> users = new HashSet<>();

        public Group(String title, Set<User> users) {
            this.title = title;
            this.users = users;
        }
    }

    public interface UserStore {
        boolean addUser(User user, Group group);
        boolean deleteUser(User user, Group group);
        Set<User> getUsers();
    }

    public class SimpleUserStore implements UserStore {
        Map<Group, User> users = new HashMap<>();

        @Override
        public boolean addUser(User user, Group group) {
            return users.put(group, user) != null;
        }

        @Override
        public boolean deleteUser(User user, Group group) {
            return users.remove(group, user);
        }

        @Override
        public Set<User> getUsers() {
            return new HashSet<>(users.values());
        }
    }
}
