package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.exchanges.CodeFileRequest;
import com.refactoringflow.refactoringflowbackend.mappers.CodeFileRequestMapper;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import com.refactoringflow.refactoringflowbackend.service.CodeFileService;
import com.refactoringflow.refactoringflowbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/codefile")
public class CodeFileController {
    private final CodeFileService codeFileService;
    private final StudentService studentService;
    private final AlgorithmRepository algorithmRepo;

    @Autowired
    public CodeFileController(CodeFileService codeFileService, StudentService studentService, AlgorithmRepository algorithmRepository) {
        this.codeFileService = codeFileService;
        this.studentService = studentService;
        this.algorithmRepo = algorithmRepository;
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

    @GetMapping("/feedback/{id}")
    public ResponseEntity<String> getFeedbackFromFile(@PathVariable long id){
        CodeFile file = codeFileService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(file.getFeedback());
    }

    @GetMapping("/template/{assignmentId}")
    public ResponseEntity<byte[]> getTemplate(@PathVariable long assignmentId){
        CodeFile file = codeFileService.getTemplate(assignmentId);
        if(file==null){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION)
                    .body("Template not created".getBytes());
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(file.getData());
    }

    @PostMapping("/")
    public ResponseEntity<String> uploadFile(@RequestBody CodeFileRequest codeFileRequest){
        CodeFileRequestMapper mapper = new CodeFileRequestMapper();
        CodeFile codeFile = mapper.toEntity(codeFileRequest);
        CodeFile template = codeFileService.findCodefileByAssignment(codeFileRequest.assignmentId);
        if(template != null) {
            codeFileService.save(codeFile,
                    codeFileRequest.assignmentId,
                    codeFileRequest.userId);
            return ResponseEntity.ok("File saved successfully");
        }else {
            codeFileService.save(codeFile,
                    codeFileRequest.assignmentId,
                    codeFileRequest.userId);
            return ResponseEntity.ok("File saved successfully");
        }
    }
}
