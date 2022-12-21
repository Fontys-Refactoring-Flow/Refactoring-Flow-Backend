package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.exchanges.CodeFileRequest;
import com.refactoringflow.refactoringflowbackend.mappers.CodeFileRequestMapper;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.service.CodeFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/file")
public class FileController {
    private final CodeFileService codeFileService;

    public FileController(CodeFileService codeFileService) {
        this.codeFileService = codeFileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody CodeFileRequest codeFileRequest){
        CodeFileRequestMapper mapper = new CodeFileRequestMapper();
        CodeFile codeFile = mapper.toEntity(codeFileRequest);
        codeFileService.save(codeFile,
                codeFileRequest.assignmentId,
                codeFileRequest.userId);
        return ResponseEntity.ok("File saved successfully");
    }
}
