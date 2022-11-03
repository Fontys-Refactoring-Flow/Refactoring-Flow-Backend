package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.exception.ResourceNotFoundException;
import com.refactoringflow.refactoringflowbackend.model.Assignment;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import com.refactoringflow.refactoringflowbackend.repository.AssignmentRepository;
import com.refactoringflow.refactoringflowbackend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentController(AssignmentService assignmentService, AssignmentRepository assignmentRepository) {
        this.assignmentService = assignmentService;
        this.assignmentRepository = assignmentRepository;
    }

    @GetMapping(value = "/")
    public List<Assignment> getAllChallenges(){
        return assignmentService.findAll();
    }

    @GetMapping(value = "/findById/{assignmentId}")
    public Assignment getChallengeById(@PathVariable long assignmentId) {
        return assignmentService.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not exist with id :" + assignmentId));
    }

    @GetMapping("/findByStudentId/{studentId}")
    List<Assignment> findAssignmentsByStudentId(@PathVariable Long studentId) {
        Student student = new Student();
        student.setId(studentId);
        return assignmentService.findAssignmentByStudent(student);
    }

    @PostMapping("/assignment")
    public Assignment createChallenge(@RequestBody Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
}
