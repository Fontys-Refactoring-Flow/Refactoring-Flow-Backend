package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.repository.UserRepository;
import com.refactoringflow.refactoringflowbackend.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final UserRepository userRepository;

    public StudentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Student> findAll(){
        return userRepository.findAll();
    }

    public Student updateStudent(Student student){
        return userRepository.save(student);
    }

    public Optional<Student> findById(Long id){
        return userRepository.findById(id);
    }

    public Student findStudentByEmailAndPassword(String email, String password) {
        return userRepository.findStudentByEmailAndPassword(email, password);
    }

    public Optional<Student> findByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<Student> findByEmail(String name) {
        return userRepository.findByEmail(name);
    }

    public void save(Student student) {
        userRepository.save(student);
    }
}
