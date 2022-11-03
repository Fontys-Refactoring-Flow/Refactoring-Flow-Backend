package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import com.refactoringflow.refactoringflowbackend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
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

    public Optional<Student> findById(Long id){
        return studentRepository.findById(id);
    }

    public Student findStudentByEmailAndPassword(String email, String password) {
        return studentRepository.findStudentByEmailAndPassword(email, password);
    }
    public List<CodeFile> findCodefileByAssignmentID(Student student, int id){
        List<CodeFile> codeFiles = new ArrayList<CodeFile>();
        for (CodeFile codeFile: student.getCodeFiles()) {

            if(codeFile.getAssignment().getId() == id){
                codeFiles.add(codeFile);
            }
        }
        return codeFiles;
    }

    public Optional<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }
}
