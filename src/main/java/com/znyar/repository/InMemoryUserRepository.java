package com.znyar.repository;

import com.znyar.model.User;
import java.util.*;

public class InMemoryUserRepository implements UserRepository {

    private static InMemoryUserRepository INSTANCE;

    private final List<User> users = new LinkedList<>();

    public static InMemoryUserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InMemoryUserRepository();
            INSTANCE.save(User.createUser("admin", "admin", User.Role.ROLE_ADMIN));
            return INSTANCE;
        }
        return INSTANCE;
    }

    private InMemoryUserRepository() {}

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

}
