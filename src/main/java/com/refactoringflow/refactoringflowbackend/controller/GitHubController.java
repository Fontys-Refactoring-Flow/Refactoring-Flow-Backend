package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.model.user.GitHub;
import com.refactoringflow.refactoringflowbackend.model.user.User;
import com.refactoringflow.refactoringflowbackend.service.GitHubService;
import com.refactoringflow.refactoringflowbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return new ResponseEntity<>("No authentication", HttpStatus.UNAUTHORIZED);
        }

        Optional<User> user = userService.findByName(token.getName());
        if(user.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        user.get().setGithub(new GitHub(code));
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
