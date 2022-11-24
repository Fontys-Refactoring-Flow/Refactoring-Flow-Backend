package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.service.GitHubService;
import com.refactoringflow.refactoringflowbackend.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/upload")
public class UploadController {
    private final GitHubService gitHubService;
    private final UserService userService;

    public UploadController(GitHubService gitHubService, UserService userService) {
        this.gitHubService = gitHubService;
        this.userService = userService;
    }

    @GetMapping("/github/list")
    public List<String> listRepo() {
        if(userService.getUserFromToken().isEmpty()) {
            return null;
        }

        return gitHubService.getUserRepositories(userService.getUserFromToken().get().getId());
    }
}
