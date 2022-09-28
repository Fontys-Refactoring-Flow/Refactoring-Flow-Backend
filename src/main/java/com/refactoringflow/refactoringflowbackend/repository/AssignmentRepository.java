package com.refactoringflow.refactoringflowbackend.repository;


import com.refactoringflow.refactoringflowbackend.model.Assignment;
import com.refactoringflow.refactoringflowbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAssignmentByRefactoringType(String refactoringType);
    List<Assignment> findByStudentsContaining(Student student);
}
