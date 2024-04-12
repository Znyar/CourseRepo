package com.znyar.repository;

import com.znyar.model.Training;
import com.znyar.model.User;
import com.znyar.util.InputValuesUtil;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InMemoryTrainingRepository implements TrainingRepository {

    @Override
    public List<Training> findAll(User user) {
        return user.getTrainings();
    }

    @Override
    public void save(User user, Training training) {
        user.getTrainings().add(training);
    }

    @Override
    public Optional<Training> findByTypeAndDate(User user, String type, Date date) {
        return user.getTrainings().stream()
                .filter(t -> t.getType().trim().equalsIgnoreCase(type)
                        && t.getFormattedDate().equals(InputValuesUtil.getStringFromDate(date)))
                .findAny();
    }

    @Override
    public void delete(User user, Training training) {
        user.getTrainings().remove(training);
    }

}
