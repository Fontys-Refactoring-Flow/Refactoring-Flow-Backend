package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.assignment.Assignment;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import com.refactoringflow.refactoringflowbackend.repository.CodeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class CodeFileService {

    private final CodeFileRepository codeFileRepository;
    private final AssignmentService assignmentService;
    private final StudentService studentService;

    @Autowired
    public CodeFileService(CodeFileRepository codeFileRepository, AssignmentService assignmentService, StudentService studentService) {
        this.codeFileRepository = codeFileRepository;
        this.assignmentService = assignmentService;
        this.studentService = studentService;
    }

    public void store(String code, int assignmentId, int userId, int version) throws IOException {
        Optional<Assignment> assignment = assignmentService.findById((long)assignmentId);
        CodeFile CodeFile = new CodeFile((version + 1),assignment.get().getRefactoringType(),"text/x-java-source", code.getBytes());
        Optional<Student> student = studentService.findById((long)userId);
        studentService.setCodeFileByAssignmentID(student.get(), assignment.get(), codeFileRepository.save(CodeFile));
    }

    public CodeFile getFile(long id){
        return codeFileRepository.findById(id).orElseThrow();
    }

}
