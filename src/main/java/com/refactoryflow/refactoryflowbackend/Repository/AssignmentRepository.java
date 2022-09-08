package com.refactoryflow.refactoryflowbackend.Repository;


import com.refactoryflow.refactoryflowbackend.Model.Assignment;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAssignmentByRefactoringType(String refactoringType);
    List<Assignment> findChallengeByStudents(Student student);
}
