package com.znyar.service.trainings;

import com.znyar.model.Training;
import com.znyar.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserTrainingsService {

    List<Training> findAll(User user);

    void addTraining(User user, Training training);

    Optional<Training> findByTypeAndDate(User user, String type, Date date);

    void deleteTraining(User user, Training training);

}
