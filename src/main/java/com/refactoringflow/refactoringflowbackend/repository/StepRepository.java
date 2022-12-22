package com.refactoringflow.refactoringflowbackend.repository;

import com.refactoringflow.refactoringflowbackend.model.codefile.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<Step, Long> {
}