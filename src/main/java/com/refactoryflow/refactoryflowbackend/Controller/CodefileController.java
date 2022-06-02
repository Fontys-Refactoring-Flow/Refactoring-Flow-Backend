package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.CodeFile;
import com.refactoryflow.refactoryflowbackend.ResponseMessage;
import com.refactoryflow.refactoryflowbackend.Service.CodeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class CodefileController {

    private CodeFileService codeFileService;

    @Autowired
    public CodefileController(CodeFileService codeFileService) {
        this.codeFileService = codeFileService;
    }


    @GetMapping("/CodeFile")
    public Stream<CodeFile> getCodeFile(){
        return codeFileService.getAllCodeFiles();
    }

    @GetMapping("CodeFile/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable long id){
        CodeFile file = codeFileService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename: " + file.getName())
                .body(file.getData());
    }

    @PostMapping("CodeFile")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
        String message;
        try{
            codeFileService.store(file);
            message = "uploaded file successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }
        catch(Exception e){
            message = "upload failed";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
