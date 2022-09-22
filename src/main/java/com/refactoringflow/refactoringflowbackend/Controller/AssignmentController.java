package com.refactoringflow.refactoringflowbackend.Controller;

import com.refactoringflow.refactoringflowbackend.Repository.AssignmentRepository;
import com.refactoringflow.refactoringflowbackend.Model.Assignment;
import com.refactoringflow.refactoringflowbackend.Model.Student;
import com.refactoringflow.refactoringflowbackend.Service.AssignmentService;
import com.refactoringflow.refactoringflowbackend.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.refactoringflow.refactoringflowbackend.Exception.ResourceNotFoundException;

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
