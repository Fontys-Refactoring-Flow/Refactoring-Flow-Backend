package com.refactoringflow.refactoringflowbackend.repository;


import com.refactoringflow.refactoringflowbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByName(String name);
    Optional<Student> findByEmail(String email);
    Student findStudentByEmailAndPassword(String email, String password);
}
