package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.model.git.GitHubRepo;
import com.refactoringflow.refactoringflowbackend.service.GitHubService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/upload")
public class UploadController {
    private final GitHubService gitHubService;

    public UploadController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/github/list")
    public GitHubRepo getRepo(){
        return gitHubService.getRepositoryContent("Fontys-Refactoring-Flow", "Refactoring-Flow-Frontend", "");
    }
}
