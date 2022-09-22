package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.repository.TeacherRepository;
import com.refactoringflow.refactoringflowbackend.model.Teacher;
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
