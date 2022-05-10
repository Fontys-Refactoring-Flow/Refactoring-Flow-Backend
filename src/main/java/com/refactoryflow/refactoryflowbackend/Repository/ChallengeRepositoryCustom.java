package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Challenge;
import com.refactoryflow.refactoryflowbackend.Model.Student;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepositoryCustom {
    List<Challenge> findChallengeByLanguage(String language);

    List<Challenge> findChallengeByStudents(Student student);
}
