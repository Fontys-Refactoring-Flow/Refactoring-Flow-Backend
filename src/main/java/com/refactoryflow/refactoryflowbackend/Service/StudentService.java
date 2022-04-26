package com.refactoryflow.refactoryflowbackend.Service;

import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Repository.StudentRepository;
import com.refactoryflow.refactoryflowbackend.Repository.StudentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentRepositoryCustom {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student){
        return studentRepository.save(student);
    }

    public Optional<Student> findStudent(Long id){
        return studentRepository.findById(id);
    }
}
