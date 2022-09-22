package com.refactoringflow.refactoringflowbackend.Controller;


import com.refactoringflow.refactoringflowbackend.Model.LearningOutcomes;
import com.refactoringflow.refactoringflowbackend.Model.Student;
import com.refactoringflow.refactoringflowbackend.Service.LearningOutcomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class LearningOutcomesController {

    LearningOutcomesService learningOutcomesService;

    @Autowired
    public LearningOutcomesController(LearningOutcomesService learningOutcomesService) {
        this.learningOutcomesService = learningOutcomesService;
    }

    @GetMapping("/learning_outcomes/{id}")
    public Optional<LearningOutcomes> findById(@PathVariable Long id){
        return  learningOutcomesService.findById(id);
    }

    @GetMapping("/learning_outcomes/studentid/{id}")
    public Optional<LearningOutcomes> findByStudentId(@PathVariable Long id){
        Student student = new Student();
        student.setId(id);
        return learningOutcomesService.findLearningOutcomesByStudent(student);
    }

    @PostMapping("/learning_outcomes")
    public LearningOutcomes updateLearningOutcomes(@RequestBody LearningOutcomes learningOutcomes){
        return learningOutcomesService.updateLearningOutcomes(learningOutcomes);
    }
}
