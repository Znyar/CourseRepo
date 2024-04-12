package com.znyar.service.trainings;

import com.znyar.model.Training;
import com.znyar.model.User;
import com.znyar.repository.InMemoryTrainingRepository;
import com.znyar.repository.TrainingRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DefaultUserTrainingService implements UserTrainingsService {

    private final TrainingRepository trainingRepository;

    public DefaultUserTrainingService() {
        trainingRepository = new InMemoryTrainingRepository();
    }

    @Override
    public List<Training> findAll(User user) {
        return trainingRepository.findAll(user);
    }

    @Override
    public void addTraining(User user, Training training) {
        Optional<Training> optionalTraining = user.getTrainings().stream()
                .filter(t ->
                        t.getFormattedDate().equals(training.getFormattedDate())
                                && t.getType().trim().equalsIgnoreCase(training.getType().trim()))
                .findAny();
        if (optionalTraining.isPresent()) {
            System.out.println("\nYou can not add training with same type and date twice\n");
        } else {
            trainingRepository.save(user, training);
            System.out.println("\nTraining is added successfully\n");
        }
    }

    @Override
    public Optional<Training> findByTypeAndDate(User user, String type, Date date) {
        return trainingRepository.findByTypeAndDate(user, type, date);
    }

    @Override
    public void deleteTraining(User user, Training training) {
        trainingRepository.delete(user, training);
    }

}
