package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.ResponseMessage;
import com.refactoringflow.refactoringflowbackend.exchanges.CodeFileRequest;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import com.refactoringflow.refactoringflowbackend.service.CodeFileService;
import com.refactoringflow.refactoringflowbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/codefile")
public class CodeFileController {
    private final CodeFileService codeFileService;
    private final StudentService studentService;
    @Autowired
    public CodeFileController(CodeFileService codeFileService, StudentService studentService) {
        this.codeFileService = codeFileService;
        this.studentService = studentService;
    }

    @GetMapping("/get")
    public List<CodeFile> getCodeFileByUser(@RequestParam String name, @RequestParam int assignmentID){
        Student student = studentService.findByName(name).orElseThrow();
        return studentService.findCodefileByAssignmentID(student, assignmentID);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable long id){
        CodeFile file = codeFileService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(file.getData());
    }

    @PostMapping("/")
    public ResponseEntity<String> uploadFile(@RequestBody CodeFileRequest codeFileRequest){
        codeFileService.save(codeFileRequest.code,
                codeFileRequest.assignmentId,
                codeFileRequest.userId,
                codeFileRequest.version);
        return ResponseEntity.ok("File saved successfully");
    }
}
