package com.refactoringflow.refactoringflowbackend.Repository;


import com.refactoringflow.refactoringflowbackend.Model.LearningOutcomes;
import com.refactoringflow.refactoringflowbackend.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearningOutcomesRepository extends JpaRepository<LearningOutcomes, Long> {
    Optional<LearningOutcomes> findLearningOutcomesByStudent(Student student);
}
