package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Teacher;

public interface TeacherRepositoryCustom {
    Teacher findTeacherByEmailAndPassword(String email, String password);

}
