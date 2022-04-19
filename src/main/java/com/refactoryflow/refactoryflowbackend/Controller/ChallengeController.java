package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.Challenge;
import com.refactoryflow.refactoryflowbackend.Repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.refactoryflow.refactoryflowbackend.Exception.ResourceNotFoundException;

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

    @GetMapping(value = "/challenge/{challengeid}")
    public Challenge getChallengeById(@PathVariable long challengeid){
        return challengeRepository.findById(challengeid)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not exist with id :" + challengeid));
    }
}
