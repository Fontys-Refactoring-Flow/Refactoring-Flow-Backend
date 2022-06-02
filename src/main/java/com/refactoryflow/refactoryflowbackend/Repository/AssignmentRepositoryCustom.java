package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Assignment;
import com.refactoryflow.refactoryflowbackend.Model.Student;

import java.util.List;

public interface AssignmentRepositoryCustom {
    List<Assignment> findChallengeByLanguage(String language);

    List<Assignment> findChallengeByStudents(Student student);
}
