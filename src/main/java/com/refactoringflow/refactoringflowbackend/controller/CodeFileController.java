package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.ResponseMessage;
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
import java.util.Map;

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
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename: " + file.getName())
                .body(file.getData());
    }

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestBody Map<String,String> MapDate){
        String message;
        codeFileService.save(MapDate.get("code"), (long)Integer.parseInt(MapDate.get("assignmentId")), (long) Integer.parseInt(MapDate.get("userId")), Integer.parseInt(MapDate.get("version")));
        try{

            message = "uploaded file successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }
        catch(Exception e){
            message = "upload failed";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
