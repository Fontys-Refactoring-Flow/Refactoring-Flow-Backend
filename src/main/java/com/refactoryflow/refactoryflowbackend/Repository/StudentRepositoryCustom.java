package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Student;

public interface StudentRepositoryCustom {
    Student findStudentByEmailAndPassword(String email, String password);
}
