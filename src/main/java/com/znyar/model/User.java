package com.znyar.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class User {

    @Getter
    public enum Role {
        ROLE_USER("User"), ROLE_ADMIN("Admin");

        private final String name;

        Role(String name) {
            this.name = name;
        }

    }

    private User() {}

    private String name;
    private String password;
    private Role role;
    private List<Training> trainings;

    public static User createUser(String name, String password, Role role) {
        User user = new User();
        user.name = name;
        user.password = password;
        user.role = role;
        user.trainings = new LinkedList<>();
        return user;
    }

}
