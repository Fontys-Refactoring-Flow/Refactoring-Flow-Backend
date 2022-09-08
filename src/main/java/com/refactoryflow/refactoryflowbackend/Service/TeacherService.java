package com.refactoryflow.refactoryflowbackend.Service;

import com.refactoryflow.refactoryflowbackend.Model.Teacher;
import com.refactoryflow.refactoryflowbackend.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher findTeacherByEmailAndPassword(String email, String password) {
        return teacherRepository.findTeacherByEmailAndPassword(email, password);
    }
}
