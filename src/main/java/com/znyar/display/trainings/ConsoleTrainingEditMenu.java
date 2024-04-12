package com.znyar.display.trainings;

import com.znyar.display.ActionDispatcher;
import com.znyar.display.Menu;
import com.znyar.model.Training;
import com.znyar.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleTrainingEditMenu implements Menu {

    private static boolean isRunning;

    private final Training currentTraining;

    private final ActionDispatcher actionDispatcher;

    public ConsoleTrainingEditMenu(User user, Training training) {
        isRunning = true;
        currentTraining = training;
        actionDispatcher = new ConsoleTrainingEditActionDispatcher(user, training);
    }

    @Override
    public void start() {
        while (isRunning) {

            System.out.println("\n"+ currentTraining.getType() + " - " + currentTraining.getFormattedDate() + "\n");

            System.out.println("1. Edit");
            System.out.println("2. Delete");
            System.out.println("3. Back");

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
