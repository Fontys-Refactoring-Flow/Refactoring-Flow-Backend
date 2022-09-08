package com.refactoryflow.refactoryflowbackend.Service;

import com.refactoryflow.refactoryflowbackend.Model.LearningOutcomes;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Repository.LearningOutcomesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LearningOutcomesService {

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

    public Optional<LearningOutcomes> findLearningOutcomesByStudent(Student student) {
        return learningOutcomesRepository.findLearningOutcomesByStudent(student);
    }
}
