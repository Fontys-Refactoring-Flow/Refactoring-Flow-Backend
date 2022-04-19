package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.Challenge;
import com.refactoryflow.refactoryflowbackend.Repository.ChallengeRepository;
import com.refactoryflow.refactoryflowbackend.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.refactoryflow.refactoryflowbackend.Exception.ResourceNotFoundException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class ChallengeController {

    ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping(value = "/challenge")
    public List<Challenge> getAllChallenges(){
        return challengeService.findAll();
    }

    @GetMapping(value = "/challenge/id/{challengeid}")
    public Challenge getChallengeById(@PathVariable long challengeid){
        return challengeService.findById(challengeid)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not exist with id :" + challengeid));
    }

    @GetMapping(value = "/challenge/subject/{subject}")
    public List<Challenge> getChallengeBySubject(@PathVariable String subject){
        return challengeService.findChallengeBySubject(subject);
    }
}
