package com.znyar.service.authentication;

import com.znyar.display.user_options.ConsoleUserOptionsMenu;
import com.znyar.model.User;
import com.znyar.repository.InMemoryUserRepository;
import com.znyar.repository.UserRepository;

import java.util.Optional;

public class DefaultAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository = InMemoryUserRepository.getInstance();

    @Override
    public void signUp(String login, String password) {
        Optional<User> optionalUser = userRepository.findByName(login);
        if (optionalUser.isEmpty()) {
            User user = User.createUser(login, password, User.Role.ROLE_USER);
            userRepository.save(user);
            System.out.println("\n" + user.getName() +", you have been signed up successfully, please sign in\n");
        } else {
            System.out.println("\nUser with name " + login + " already exists\n");
        }
    }

    @Override
    public void signIn(String login, String password) {
        Optional<User> optionalUser = userRepository.findByName(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                System.out.println("\n" + user.getName() + ", you have been signed in successfully\n");
                ConsoleUserOptionsMenu consoleUserOptionsMenu = new ConsoleUserOptionsMenu(user);
                consoleUserOptionsMenu.start();
            } else {
                System.out.println("\nIncorrect password\n");
            }
        } else {
            System.out.println("\nUser is not found\n");
        }
    }

}
