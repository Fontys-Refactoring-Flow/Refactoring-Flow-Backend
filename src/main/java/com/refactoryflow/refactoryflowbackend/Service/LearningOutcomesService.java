package com.refactoryflow.refactoryflowbackend.Service;

import com.refactoryflow.refactoryflowbackend.Model.LearningOutcomes;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Model.User;
import com.refactoryflow.refactoryflowbackend.Repository.LearningOutcomesRepository;
import com.refactoryflow.refactoryflowbackend.Repository.LearningOutcomesRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LearningOutcomesService implements LearningOutcomesRepositoryCustom {

    @Autowired
    private final LearningOutcomesRepository learningOutcomesRepository;

    public LearningOutcomesService(LearningOutcomesRepository learningOutcomesRepository) {
        this.learningOutcomesRepository = learningOutcomesRepository;
    }

    public LearningOutcomes updateLearningOutcomes(LearningOutcomes learningOutcomes){
        return learningOutcomesRepository.save(learningOutcomes);
    }

    public Optional<LearningOutcomes> findById(Long id){
        return learningOutcomesRepository.findById(id);
    }

    @Override
    public Optional<LearningOutcomes> findLearningOutcomesByUser(User user) {
        return learningOutcomesRepository.findLearningOutcomesByUser(user);
    }
}
