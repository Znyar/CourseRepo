package com.znyar.display.trainings;

import com.znyar.display.ActionDispatcher;
import com.znyar.model.Training;
import com.znyar.model.User;
import com.znyar.service.trainings.DefaultUserTrainingService;
import com.znyar.service.trainings.UserTrainingsService;
import com.znyar.util.InputValuesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Map;

public class ConsoleTrainingEditActionDispatcher implements ActionDispatcher {

    private final Training currentTraining;

    private final User currentUser;

    private final UserTrainingsService trainingsService;

    public ConsoleTrainingEditActionDispatcher(User user, Training training) {
        currentTraining = training;
        currentUser = user;
        trainingsService = new DefaultUserTrainingService();
    }
    @Override
    public void dispatch(int actionIndex) {
        switch (actionIndex) {
            case 1 -> editTrainingFromConsole();
            case 2 -> {
                System.out.print("\nAre you sure? (Input \"Y\" if you are or anything if not): ");
                String answer = InputValuesUtil.inputString();
                if (answer.equals("Y")) {
                    trainingsService.deleteTraining(currentUser, currentTraining);
                    System.out.println("\nTraining is successfully deleted.\n");
                    ConsoleTrainingEditMenu.stop();
                }
            }
            case 3 -> ConsoleTrainingEditMenu.stop();
            default -> System.out.println("\nInput error. Please input a number corresponding to the action.\n");
        }
    }

    private void editTrainingFromConsole() {
        System.out.println("\nEdit training: ");
        System.out.print("Duration (in minutes): ");
        currentTraining.setDuration(Duration.ofMinutes(Long.parseLong(InputValuesUtil.inputString())));
        System.out.print("Spent calories (in kcal): ");
        currentTraining.setSpentCalories(Integer.parseInt(InputValuesUtil.inputString()));
        currentTraining.getAdditionalInfo().forEach((key, value) -> {
            System.out.print(key + ": ");
            currentTraining.getAdditionalInfo().put(key, Long.parseLong(InputValuesUtil.inputString()));
        });
        System.out.print("\nDo you want to add additional info? (Input \"Y\" if you are or anything if not): ");
        String answer = InputValuesUtil.inputString();
        if (answer.equals("Y")) {
            currentTraining.getAdditionalInfo().putAll(InputValuesUtil.inputAdditionalValuesFromConsole());
        }
    }

}
