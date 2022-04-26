package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.Challenge;
import com.refactoryflow.refactoryflowbackend.Model.LearningOutcomes;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    List<Student> findAllStudents(){
        return studentService.findAll();
    }

    @PostMapping("/student")
    public Student startChallenge(@RequestBody Student student){
        return studentService.updateStudent(student);
    }
}
