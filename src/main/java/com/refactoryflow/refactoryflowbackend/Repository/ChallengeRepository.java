package com.refactoryflow.refactoryflowbackend.Repository;


import com.refactoryflow.refactoryflowbackend.Model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>,
        ChallengeRepositoryCustom {
}
