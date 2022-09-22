package com.refactoringflow.refactoringflowbackend.Controller;

import com.refactoringflow.refactoringflowbackend.Model.Student;
import com.refactoringflow.refactoringflowbackend.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/student/{id}")
    public Optional<Student> findById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @PostMapping("/student/update_student")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @GetMapping("/student/login/{email}/{password}")
    public Student login(@PathVariable String email, @PathVariable String password){
        return studentService.findStudentByEmailAndPassword(email, password);
    }
}
