package com.znyar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InputValuesUtil {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public static boolean isCorrectLoginAndPassword(String login, String password) {
            return !login.equals("") && !password.equals("");
    }

    public static Optional<Date> getDateFromString(String date) {
        try {
            return Optional.of(sdf.parse(date));
        } catch (ParseException e) {
            System.out.println("Date is not correct");
        }
        return Optional.empty();
    }

    public static String getStringFromDate(Date date) {
        return sdf.format(date);
    }

    public static String inputString() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String value = "";
        try {
            value = reader.readLine();
        } catch (IOException e) {
            System.out.println("\nInput error.\n");
        }
        return value;
    }

    public static Map<String, Long> inputAdditionalValuesFromConsole() {
        boolean isRunning = true;
        Map<String, Long> values = new HashMap<>();
        while (isRunning) {
            System.out.print("Parameter name: ");
            String name = InputValuesUtil.inputString();
            System.out.print("Parameter value: ");
            String value = InputValuesUtil.inputString();
            values.put(name, Long.parseLong(value));
            System.out.print("\nDo you want to add another parameter? (Input \"Y\" if you are or anything if not): ");
            String answer = InputValuesUtil.inputString();
            if (!answer.equals("Y")) {
                isRunning = false;
            }
        }
        return values;
    }

}
