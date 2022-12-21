package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.model.user.Teacher;
import com.refactoringflow.refactoringflowbackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/login/{email}/{password}")
    public Teacher login(@PathVariable String email, @PathVariable String password){
        return teacherService.findTeacherByEmailAndPassword(email, password);
    }
}
