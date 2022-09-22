package com.refactoringflow.refactoringflowbackend.Repository;


import com.refactoringflow.refactoringflowbackend.Model.Assignment;
import com.refactoringflow.refactoringflowbackend.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAssignmentByRefactoringType(String refactoringType);
    List<Assignment> findChallengeByStudents(Student student);
}
