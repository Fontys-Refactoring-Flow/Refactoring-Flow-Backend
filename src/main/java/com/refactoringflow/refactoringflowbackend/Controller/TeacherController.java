package com.refactoringflow.refactoringflowbackend.Controller;

import com.refactoringflow.refactoringflowbackend.Model.Teacher;
import com.refactoringflow.refactoringflowbackend.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class TeacherController {

    TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/teacher/login/{email}/{password}")
    public Teacher login(@PathVariable String email, @PathVariable String password){
        return teacherService.findTeacherByEmailAndPassword(email, password);
    }
}
