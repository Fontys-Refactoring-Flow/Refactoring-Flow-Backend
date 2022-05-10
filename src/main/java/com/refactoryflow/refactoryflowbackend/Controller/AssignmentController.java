package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.Assignment;
import com.refactoryflow.refactoryflowbackend.Model.Student;
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
    StudentService studentService;

    @Autowired
    private AssignmentRepository assignmentRepository;

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

    @GetMapping(value = "/assignment/language/{language}")
    public List<Assignment> getChallengeByLanguage(@PathVariable String language){
        return assignmentService.findChallengeByLanguage(language);
    }

    @GetMapping("/assignment/{studentid}")
    List<Assignment> findChallengeByStudentId(@PathVariable Long studentid){
        Student student = new Student();
        student.setId(studentid);
        return assignmentService.findChallengeByStudents(student);
    }
    @PostMapping("/assignment")
    public Assignment createChallenge(@RequestBody Assignment assignment){
        return assignmentRepository.save(assignment);
    }
}
