package com.refactoryflow.refactoryflowbackend.Service;

import com.refactoryflow.refactoryflowbackend.Model.Challenge;
import com.refactoryflow.refactoryflowbackend.Repository.ChallengeRepository;
import com.refactoryflow.refactoryflowbackend.Repository.ChallengeRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService implements ChallengeRepositoryCustom {

    @Autowired
    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<Challenge> findAll() {
        return challengeRepository.findAll();
    }

    public Optional<Challenge> findById(long challengeid) {
        return challengeRepository.findById(challengeid);
    }

    @Override
    public List<Challenge> findChallengeBySubject(String subject) {
        return challengeRepository.findChallengeBySubject(subject);
    }
}
