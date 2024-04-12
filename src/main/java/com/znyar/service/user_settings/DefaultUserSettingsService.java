package com.znyar.service.user_settings;

import com.znyar.model.User;
import com.znyar.repository.InMemoryUserRepository;
import com.znyar.repository.UserRepository;

public class DefaultUserSettingsService implements UserSettingsService {

    private final UserRepository userRepository = InMemoryUserRepository.getInstance();

    @Override
    public void updateLoginAndPassword(User user, String login, String password) {
        if (!user.getPassword().equals(password) || !user.getName().equals(login)) {
            user.setName(login);
            user.setPassword(password);
        } else {
            System.out.println("\nLogin or password are the same\n");
        }
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void findAllUsers() {
        System.out.println("\nUsers");
        userRepository.findAll().forEach(user ->
                System.out.println("\n" + user.getName() + ", " +
                        "role: " + user.getRole().getName() + ", " +
                        "password: " + user.getPassword())
        );
        System.out.println("\n");
    }

}
