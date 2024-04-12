package com.znyar.repository;

import com.znyar.model.Training;
import com.znyar.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingRepository {

    List<Training> findAll(User user);

    void save(User user, Training training);

    Optional<Training> findByTypeAndDate(User user, String type, Date date);

    void delete(User user, Training training);

}
