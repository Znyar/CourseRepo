package com.znyar.model;

import com.znyar.util.InputValuesUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@Getter
@Setter
public class Training {

    private String type;
    private Date date;
    private Duration duration;
    private int spentCalories;
    private Map<String, Long> additionalInfo;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    private Training() {}

    public static Training createTraining(String type, Duration duration, int spentCalories, Map<String, Long> additionalInfo) {
        Training training = new Training();
        training.setType(type);
        training.setDuration(duration);
        training.setSpentCalories(spentCalories);
        training.additionalInfo = additionalInfo;
        training.date = new Date();
        return training;
    }

    public String getFormattedDate() {
        return InputValuesUtil.getStringFromDate(date);
    }

}
