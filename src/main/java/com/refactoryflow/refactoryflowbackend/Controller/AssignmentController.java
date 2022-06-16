package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.Assignment;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Model.User;
import com.refactoryflow.refactoryflowbackend.Repository.AssignmentRepository;
import com.refactoryflow.refactoryflowbackend.Service.AssignmentService;
import com.refactoryflow.refactoryflowbackend.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.refactoryflow.refactoryflowbackend.Exception.ResourceNotFoundException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class AssignmentController {

    AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping(value = "/assignment")
    public List<Assignment> getAllChallenges(){
        return assignmentService.findAll();
    }

    @GetMapping(value = "/assignment/id/{assignmentid}")
    public Assignment getChallengeById(@PathVariable long assignmentid){
        return assignmentService.findById(assignmentid)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not exist with id :" + assignmentid));
    }

    @GetMapping(value = "/assignment/refactoring_type/{refactoringType}")
    public List<Assignment> findChallengeByRefactoringType(@PathVariable String refactoringType){
        return assignmentService.findChallengeByRefactoringType(refactoringType);
    }

    @GetMapping("/assignment/{userid}")
    List<Assignment> findChallengeByUserId(@PathVariable Long userid){
        User user = new User();
        user.setId(userid);
        return assignmentService.findChallengeByUsers(user);
    }

    @PostMapping("/assignment")
    public Assignment createChallenge(@RequestBody Assignment assignment){
        return assignmentService.save(assignment);
    }
}
