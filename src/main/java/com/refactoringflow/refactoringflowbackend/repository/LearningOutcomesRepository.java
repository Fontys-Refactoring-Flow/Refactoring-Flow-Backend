package com.refactoringflow.refactoringflowbackend.repository;


import com.refactoringflow.refactoringflowbackend.model.LearningOutcomes;
import com.refactoringflow.refactoringflowbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearningOutcomesRepository extends JpaRepository<LearningOutcomes, Long> {
    Optional<LearningOutcomes> findLearningOutcomesByStudent(Student student);
}
