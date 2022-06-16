package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Assignment;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Model.User;

import java.util.List;

public interface AssignmentRepositoryCustom {
    List<Assignment> findChallengeByRefactoringType(String refactoringType);

    List<Assignment> findChallengeByUsers(User user);
}
