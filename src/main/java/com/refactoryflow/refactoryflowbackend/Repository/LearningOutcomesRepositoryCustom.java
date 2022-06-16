package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.LearningOutcomes;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Model.User;

import java.util.Optional;

public interface LearningOutcomesRepositoryCustom {

    Optional<LearningOutcomes> findLearningOutcomesByUser(User user);
}
