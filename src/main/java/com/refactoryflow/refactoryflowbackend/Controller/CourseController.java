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
public class    CourseController {

    ChallengeService challengeService;

    @Autowired
    public CourseController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

//    @GetMapping(value = "/Course")
//    public List<Challenge> getAllChallenges(){
//        return challengeService.findAll();
//    }

    @GetMapping(value = "/Course/id/{Courseid}")
    public Challenge GetCourseById(@PathVariable long Courseid){
        return challengeService.findById(Courseid)
                .orElseThrow(() -> new ResourceNotFoundException("Course does not exist with id :" + Courseid));
    }

    @GetMapping(value = "/course/Title/{title}")
    public List<Challenge> getChallengeBySubject(@PathVariable String subject){
        return challengeService.findChallengeBySubject(subject); //needs to be changed. currently not used, so no problems occur yet
    }
}
