package com.znyar.display.user_options;

import com.znyar.display.ActionDispatcher;
import com.znyar.display.Menu;
import com.znyar.display.trainings.ConsoleUserTrainingsMenu;
import com.znyar.model.User;
import com.znyar.service.user_settings.DefaultUserSettingsService;
import com.znyar.service.user_settings.UserSettingsService;
import com.znyar.util.InputValuesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserOptionsActionDispatcher implements ActionDispatcher {

    private final User currentUser;
    private final UserSettingsService userSettingsService;

    public ConsoleUserOptionsActionDispatcher(User user) {
        userSettingsService = new DefaultUserSettingsService();
        currentUser = user;
    }

    @Override
    public void dispatch(int actionIndex) {
        switch (actionIndex) {
            case 1 -> {
                Menu trainingsMenu = new ConsoleUserTrainingsMenu(currentUser);
                trainingsMenu.start();
            }
            case 2 -> {
                System.out.print("New login: ");
                String login = inputValueFromConsole();
                System.out.print("New password: ");
                String password = inputValueFromConsole();
                System.out.print("Confirm password: ");
                String confirmedPassword = inputValueFromConsole();
                if (InputValuesUtil.isCorrectLoginAndPassword(login, password) && confirmedPassword.equals(password)) {
                    userSettingsService.updateLoginAndPassword(currentUser, login, password);
                    ConsoleUserOptionsMenu.stop();
                    System.out.println("\nLogin and password are successfully updated. Please sign in again\n");
                } else {
                    System.out.println("\nLogin and password can not be empty.\n");
                }
            }
            case 3 -> {
                System.out.print("\nAre you sure? (Input \"Y\" if you are or anything if not): ");
                String answer = inputValueFromConsole();
                if (answer.equals("Y")) {
                    userSettingsService.deleteUser(currentUser);
                    System.out.println("\nUser is successfully deleted.\n");
                    ConsoleUserOptionsMenu.stop();
                }
            }
            case 4 -> {
                System.out.println("\nYou have been logged out.\n");
                ConsoleUserOptionsMenu.stop();
            }
            case 5 -> {
                if (currentUser.getRole().equals(User.Role.ROLE_ADMIN)) {
                    userSettingsService.findAllUsers();
                } else {
                    System.out.println("\nInput error. Please input a number corresponding to the action.\n");
                }
            }
            default -> System.out.println("\nInput error. Please input a number corresponding to the action.\n");
        }
    }

    private String inputValueFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String value = "";
        try {
            value = reader.readLine();
        } catch (IOException e) {
            System.out.println("\nInput error.\n");
        }
        return value;
    }

}
