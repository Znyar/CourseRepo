package com.znyar.display.authentication;

import com.znyar.display.ActionDispatcher;
import com.znyar.display.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleAuthenticationMenu implements Menu {

    private static boolean isRunning;
    private final ActionDispatcher actionDispatcher;

    public ConsoleAuthenticationMenu() {
        isRunning = true;
        actionDispatcher = new ConsoleAuthenticationActionDispatcher();
    }

    @Override
    public void start() {

        System.out.println("\nWelcome to the Training Diary App!\n\nPlease sign in or sign up.\n");

        while (isRunning) {

            System.out.println("1. Sign in");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");

            System.out.print("\nInput action (number): ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                actionDispatcher.dispatch(Integer.parseInt(reader.readLine()));
            } catch (NumberFormatException | IOException e) {
                System.out.println("Input error. Please enter a valid number.\n");
            }
        }
    }

    protected static void stop() {
        isRunning = false;
    }

}
