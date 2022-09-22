package com.refactoringflow.refactoringflowbackend.Repository;

import com.refactoringflow.refactoringflowbackend.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findTeacherByEmailAndPassword(String email, String password);
}
