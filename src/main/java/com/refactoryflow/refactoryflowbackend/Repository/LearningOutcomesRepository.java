package com.refactoryflow.refactoryflowbackend.Repository;


import com.refactoryflow.refactoryflowbackend.Model.LearningOutcomes;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearningOutcomesRepository extends JpaRepository<LearningOutcomes, Long> {
    Optional<LearningOutcomes> findLearningOutcomesByStudent(Student student);
}
