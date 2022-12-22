package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.model.codefile.StepDTO;
import com.refactoringflow.refactoringflowbackend.service.CodeFileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/steps")
public class StepsController {
    private final CodeFileService codeFileService;

    public StepsController(CodeFileService codeFileService) {
        this.codeFileService = codeFileService;
    }
    @PostMapping(value = "/{codeFileId}/")
    public List<StepDTO> getSteps(@PathVariable Long codeFileId) {
        return codeFileService.getSteps(codeFileService.getFile(codeFileId));
    }
}
