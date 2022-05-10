package com.refactoryflow.refactoryflowbackend.Repository;


import com.refactoryflow.refactoryflowbackend.Model.LearningOutcomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningOutcomesRepository extends JpaRepository<LearningOutcomes, Long>,
        LearningOutcomesRepositoryCustom{
}
