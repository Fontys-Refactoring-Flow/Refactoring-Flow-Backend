package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.Challenge;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Repository.ChallengeRepository;
import com.refactoryflow.refactoryflowbackend.Service.ChallengeService;
import com.refactoryflow.refactoryflowbackend.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.refactoryflow.refactoryflowbackend.Exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class ChallengeController {

    ChallengeService challengeService;

    @Autowired
    StudentService studentService;

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

    @GetMapping(value = "/challenge/language/{language}")
    public List<Challenge> getChallengeByLanguage(@PathVariable String language){
        return challengeService.findChallengeByLanguage(language);
    }

    @GetMapping("/challenge/{studentid}")
    List<Challenge> findChallengeByStudentId(@PathVariable Long studentid){
        Optional<Student> student = studentService.findStudent(studentid);
        return challengeService.findChallengeByStudents(student);
    }
}
