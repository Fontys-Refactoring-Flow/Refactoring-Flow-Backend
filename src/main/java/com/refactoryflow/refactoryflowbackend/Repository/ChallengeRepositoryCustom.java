package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Challenge;

import java.util.List;

public interface ChallengeRepositoryCustom {
    List<Challenge> findChallengeBySubject(String subject);
}
