package com.znyar.display.trainings;

import com.znyar.display.ActionDispatcher;
import com.znyar.display.Menu;
import com.znyar.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserTrainingsMenu implements Menu {

    private static boolean isRunning;

    private final ActionDispatcher actionDispatcher;

    public ConsoleUserTrainingsMenu(User user) {
        isRunning = true;
        actionDispatcher = new ConsoleUserTrainingsActionDispatcher(user);
    }

    @Override
    public void start() {
        while (isRunning) {

            System.out.println("\nTrainings menu\n");

            System.out.println("1. Trainings list");
            System.out.println("2. Add training");
            System.out.println("3. Edit training");
            System.out.println("4. Back");

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
