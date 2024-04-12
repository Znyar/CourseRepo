package com.znyar.repository;

import com.znyar.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void delete(User user);

    List<User> findAll();

    Optional<User> findByName(String name);

}
