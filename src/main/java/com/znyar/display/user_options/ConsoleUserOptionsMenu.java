package com.znyar.display.user_options;

import com.znyar.display.ActionDispatcher;
import com.znyar.display.Menu;
import com.znyar.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserOptionsMenu implements Menu {

    private static boolean isRunning;

    private final User currentUser;

    private final ActionDispatcher actionDispatcher;

    public ConsoleUserOptionsMenu(User user) {
        isRunning = true;
        currentUser = user;
        actionDispatcher = new ConsoleUserOptionsActionDispatcher(user);
    }

    @Override
    public void start() {
        while (isRunning) {
            System.out.println(currentUser.getName() + "'s profile\n");

            System.out.println("1. Trainings");
            System.out.println("2. Edit login and password");
            System.out.println("3. Delete account");
            System.out.println("4. Logout");

            if (currentUser.getRole().equals(User.Role.ROLE_ADMIN)) {
                System.out.println("5. Users info");
            }

            System.out.print("\nInput action (number): ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                actionDispatcher.dispatch(Integer.parseInt(reader.readLine()));
            } catch (NumberFormatException | IOException e) {
                System.out.println("\nInput error. Please enter a valid number.\n");
            }
        }

    }

    protected static void stop() {
        isRunning = false;
    }

}
