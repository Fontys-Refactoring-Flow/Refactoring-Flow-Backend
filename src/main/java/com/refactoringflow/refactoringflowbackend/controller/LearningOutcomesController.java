package com.refactoringflow.refactoringflowbackend.controller;


import com.refactoringflow.refactoringflowbackend.model.LearningOutcomes;
import com.refactoringflow.refactoringflowbackend.model.Student;
import com.refactoringflow.refactoringflowbackend.service.LearningOutcomesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/learning_outcomes")
public class LearningOutcomesController {
    private final LearningOutcomesService learningOutcomesService;

    @Autowired
    public LearningOutcomesController(LearningOutcomesService learningOutcomesService) {
        this.learningOutcomesService = learningOutcomesService;
    }

    @GetMapping("/{id}")
    public Optional<LearningOutcomes> findById(@PathVariable Long id){
        return  learningOutcomesService.findById(id);
    }

    @GetMapping("/studentId/{id}")
    public Optional<LearningOutcomes> findByStudentId(@PathVariable Long id){
        Student student = new Student();
        student.setId(id);
        return learningOutcomesService.findLearningOutcomesByStudent(student);
    }

    @PostMapping("/")
    public LearningOutcomes updateLearningOutcomes(@RequestBody LearningOutcomes learningOutcomes){
        return learningOutcomesService.updateLearningOutcomes(learningOutcomes);
    }
}
