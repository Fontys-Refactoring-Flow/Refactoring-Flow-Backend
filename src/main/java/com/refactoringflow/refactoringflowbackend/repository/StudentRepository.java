package com.refactoringflow.refactoringflowbackend.repository;

import com.refactoringflow.refactoringflowbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByEmailAndPassword(String email, String password);

    Optional<Student> findByName(String name);

    Optional<Student> findByEmail(String email);
}
