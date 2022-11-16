package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.AssignmentCodeFileStudent;
import com.refactoringflow.refactoringflowbackend.model.assignment.Assignment;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import com.refactoringflow.refactoringflowbackend.repository.AssigmentCodeFileStudentRepository;
import com.refactoringflow.refactoringflowbackend.repository.AssignmentRepository;
import com.refactoringflow.refactoringflowbackend.repository.CodeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodeFileService {

    private final CodeFileRepository codeFileRepository;
    private final AssignmentRepository assignmentRepository;
    private final AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository;
    private final StudentService studentService;

    @Autowired
    public CodeFileService(CodeFileRepository codeFileRepository,
                           AssignmentRepository assignmentRepository,
                           AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository,
                           StudentService studentService) {
        this.codeFileRepository = codeFileRepository;
        this.assignmentRepository = assignmentRepository;
        this.assigmentCodeFileStudentRepository = assigmentCodeFileStudentRepository;
        this.studentService = studentService;
    }

    public void save(String code, Long assignmentId, Long userId, int version) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Optional<Student> student = studentService.findById(userId);
        CodeFile codeFile = new CodeFile((version + 1),
                assignment.orElseThrow().getRefactoringType(),
                "text/x-java-source",
                code.getBytes());

        codeFileRepository.save(codeFile);
        assigmentCodeFileStudentRepository.save(
                new AssignmentCodeFileStudent(student.get(),
                        assignment.orElseThrow(),
                        codeFile)
        );
    }

    public CodeFile getFile(long id){
        return codeFileRepository.findById(id).orElseThrow();
    }

}
