package com.refactoringflow.refactoringflowbackend.repository;

import com.refactoringflow.refactoringflowbackend.model.AssignmentCodeFileStudent;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigmentCodeFileStudentRepository extends JpaRepository<AssignmentCodeFileStudent, Long> {
    List<AssignmentCodeFileStudent> findAssignmentCodeFilesStudentByStudent(Student student);
}
