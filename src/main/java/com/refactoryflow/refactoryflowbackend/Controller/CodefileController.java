package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.CodeFile;
import com.refactoryflow.refactoryflowbackend.Repository.CodeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class CodefileController {

    @Autowired
    private CodeFileRepository codeFileRepository;

    @GetMapping("/CodeFile")
    public List<CodeFile> getCodeFile(){
        return codeFileRepository.findAll();
    }

    @PostMapping("/CodeFile")
    public CodeFile createCodefile(@RequestBody CodeFile codefile){
        return codeFileRepository.save(codefile);
    }
}
