package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.Challenge;
import com.refactoryflow.refactoryflowbackend.Repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class ChallengeController {

    @Autowired
    private ChallengeRepository challengeRepository;

    @GetMapping(value = "/challenge")
    public List<Challenge> getAllChallenges(){
        return challengeRepository.findAll();
    }
}
