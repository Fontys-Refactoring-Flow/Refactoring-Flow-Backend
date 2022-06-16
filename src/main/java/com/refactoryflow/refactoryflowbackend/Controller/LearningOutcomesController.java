package com.refactoryflow.refactoryflowbackend.Controller;


import com.refactoryflow.refactoryflowbackend.Model.LearningOutcomes;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Model.User;
import com.refactoryflow.refactoryflowbackend.Service.LearningOutcomesService;
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

    @GetMapping("/learning_outcomes/userid/{id}")
    public Optional<LearningOutcomes> findByUserId(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        return learningOutcomesService.findLearningOutcomesByUser(user);
    }

    @PostMapping("/learning_outcomes")
    public LearningOutcomes updateLearningOutcomes(@RequestBody LearningOutcomes learningOutcomes){
        return learningOutcomesService.updateLearningOutcomes(learningOutcomes);
    }
}
