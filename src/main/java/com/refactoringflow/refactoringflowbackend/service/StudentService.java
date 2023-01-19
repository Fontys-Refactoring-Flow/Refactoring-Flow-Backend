package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.AssignmentCodeFileStudent;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import com.refactoringflow.refactoringflowbackend.repository.AssigmentCodeFileStudentRepository;
import com.refactoringflow.refactoringflowbackend.repository.AssignmentRepository;
import com.refactoringflow.refactoringflowbackend.repository.CodeFileRepository;
import com.refactoringflow.refactoringflowbackend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository;
    private final CodeFileRepository codeFileRepository;

    public StudentService(StudentRepository studentRepository, AssignmentRepository assignmentRepository,
                          CodeFileRepository codeFileRepository,
                          AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository) {
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
        this.assigmentCodeFileStudentRepository = assigmentCodeFileStudentRepository;
        this.codeFileRepository = codeFileRepository;
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
    public List<CodeFile> findCodefileByAssignmentID(Student student, int id) {
        List<AssignmentCodeFileStudent> assignmentCodeFileStudents =
                assigmentCodeFileStudentRepository.findAssignmentCodeFilesStudentByStudent(student);
        List<CodeFile> codeFiles = new ArrayList<>();
        for (AssignmentCodeFileStudent assignmentCodeFileStudent: assignmentCodeFileStudents) {

            if(assignmentCodeFileStudent.getAssignment().getId() == id){
                codeFiles.add(assignmentCodeFileStudent.getCodeFile());
            }
        }
        return codeFiles;
    }

    public Optional<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }
}
