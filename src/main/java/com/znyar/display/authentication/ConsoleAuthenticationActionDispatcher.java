package com.znyar.display.authentication;

import com.znyar.display.ActionDispatcher;
import com.znyar.service.authentication.AuthenticationService;
import com.znyar.service.authentication.DefaultAuthenticationService;
import com.znyar.util.InputValuesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleAuthenticationActionDispatcher implements ActionDispatcher {

    private final AuthenticationService authenticationService;

    public ConsoleAuthenticationActionDispatcher() {
        authenticationService = new DefaultAuthenticationService();
    }

    @Override
    public void dispatch(int actionIndex) {
        switch (actionIndex) {
            case 1 -> {
                System.out.print("Login: ");
                String login = inputValueFromConsole();
                System.out.print("Password: ");
                String password = inputValueFromConsole();
                if (InputValuesUtil.isCorrectLoginAndPassword(login, password)) {
                    authenticationService.signIn(login, password);
                } else {
                    System.out.println("\nLogin and password can not be empty.\n");
                }
            }
            case 2 -> {
                System.out.print("Login: ");
                String login = inputValueFromConsole();
                System.out.print("Password: ");
                String password = inputValueFromConsole();
                System.out.print("Confirm password: ");
                String confirmedPassword = inputValueFromConsole();
                if (InputValuesUtil.isCorrectLoginAndPassword(login, password)) {
                    if (confirmedPassword.equals(password)) {
                        authenticationService.signUp(login, password);
                    } else {
                        System.out.println("\nPasswords do not match.\n");
                    }
                } else {
                    System.out.println("\nLogin and password can not be empty.\n");
                }
            }
            case 3 -> {
                System.out.println("\nGoodbye!");
                ConsoleAuthenticationMenu.stop();
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
            System.out.println("Input error.\n");
        }
        return value;
    }

}
