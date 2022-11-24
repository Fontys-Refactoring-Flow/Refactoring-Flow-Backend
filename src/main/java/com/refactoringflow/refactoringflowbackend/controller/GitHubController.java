package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.model.user.User;
import com.refactoringflow.refactoringflowbackend.service.GitHubService;
import com.refactoringflow.refactoringflowbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/github")
public class GitHubController {
    private final GitHubService gitHubService;
    private final UserService userService;

    public GitHubController(GitHubService gitHubService, UserService userService) {
        this.gitHubService = gitHubService;
        this.userService = userService;
    }

    @PostMapping("/callback")
    public ResponseEntity<String> gitHubCallback(@RequestParam String code) {
        if(userService.getUserFromToken().isEmpty()) {
            return new ResponseEntity<>("Not authorized", HttpStatus.UNAUTHORIZED);
        }


        User user = userService.getUserFromToken().get();
        gitHubService.exchangeCode(user, code);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
