package com.refactoringflow.refactoringflowbackend.repository;

import com.refactoringflow.refactoringflowbackend.model.user.GitHub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GitHubRepository extends JpaRepository<GitHub, Long> {
}
