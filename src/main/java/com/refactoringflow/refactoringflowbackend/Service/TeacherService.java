package com.refactoringflow.refactoringflowbackend.Service;

import com.refactoringflow.refactoringflowbackend.Repository.TeacherRepository;
import com.refactoringflow.refactoringflowbackend.Model.Teacher;
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
