package com.znyar.display.trainings;

import com.znyar.display.ActionDispatcher;
import com.znyar.display.Menu;
import com.znyar.model.Training;
import com.znyar.model.User;
import com.znyar.service.trainings.DefaultUserTrainingService;
import com.znyar.service.trainings.UserTrainingsService;
import com.znyar.util.InputValuesUtil;

import java.time.Duration;
import java.util.*;

public class ConsoleUserTrainingsActionDispatcher implements ActionDispatcher {

    private final User currentUser;
    private final UserTrainingsService userTrainingsService;

    public ConsoleUserTrainingsActionDispatcher(User user) {
        userTrainingsService = new DefaultUserTrainingService();
        currentUser = user;
    }

    @Override
    public void dispatch(int actionIndex) {
        switch (actionIndex) {
            case 1 -> {
                List<Training> trainings = userTrainingsService.findAll(currentUser);
                outputTrainingsList(trainings);
            }
            case 2 -> userTrainingsService.addTraining(currentUser, inputTrainingFromConsole());
            case 3 -> {
                System.out.println("\nEnter training type and date: ");
                System.out.print("Training type: ");
                String type = InputValuesUtil.inputString();
                System.out.print("Training date (yyyy-mm-dd): ");
                String stringDate = InputValuesUtil.inputString();
                Optional<Date> date = InputValuesUtil.getDateFromString(stringDate);
                if (date.isPresent()) {
                    Optional<Training> training = userTrainingsService.findByTypeAndDate(currentUser, type, date.get());
                    if (training.isPresent()) {
                        Menu menu = new ConsoleTrainingEditMenu(currentUser, training.get());
                        menu.start();
                    }
                } else {
                    System.out.println("\nNo training found\n");
                }
            }
            case 4 -> ConsoleUserTrainingsMenu.stop();
            default -> System.out.println("\nInput error. Please input a number corresponding to the action.\n");
        }
    }

    private Training inputTrainingFromConsole() {
        System.out.println("\nNew training: ");
        System.out.print("Training type: ");
        String type = InputValuesUtil.inputString();
        System.out.print("Duration (in minutes): ");
        Duration duration = Duration.ofMinutes(Long.parseLong(InputValuesUtil.inputString()));
        System.out.print("Spent calories (in kcal): ");
        int calories = Integer.parseInt(InputValuesUtil.inputString());
        Map<String, Long> additionalInfo = new HashMap<>();
        System.out.print("\nDo you want to add additional info? (Input \"Y\" if you are or anything if not): ");
        String answer = InputValuesUtil.inputString();
        if (answer.equals("Y")) {
            additionalInfo = InputValuesUtil.inputAdditionalValuesFromConsole();
        }
        return Training.createTraining(type, duration, calories, additionalInfo);
    }

    private void outputTrainingsList(List<Training> trainings) {
        if (!trainings.isEmpty()) {
            System.out.println("\nTrainings:\n");
            trainings.stream()
                    .sorted(Comparator.comparing(Training::getDate, Comparator.nullsLast(Comparator.reverseOrder())))
                    .forEach(training -> {
                        System.out.println("------------------------------------------------\n");
                        System.out.println(training.getType());
                        System.out.println("Date: " + InputValuesUtil.getStringFromDate(training.getDate()));
                        System.out.println("Duration: " + training.getDuration().toMinutes() + " minutes");
                        System.out.println("Spent calories: " + training.getSpentCalories() + " kcal");
                        training.getAdditionalInfo().forEach(
                                (key, value) -> System.out.println(key + ": " + value)
                        );
                        System.out.println("\n------------------------------------------------\n");
                    });
        } else {
            System.out.println("\nNo trainings yet\n");
        }
    }

}
