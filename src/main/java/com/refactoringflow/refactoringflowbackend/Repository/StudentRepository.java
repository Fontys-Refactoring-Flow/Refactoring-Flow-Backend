package com.refactoringflow.refactoringflowbackend.Repository;


import com.refactoringflow.refactoringflowbackend.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByEmailAndPassword(String email, String password);
}
