package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.course.Course;
import com.refactoringflow.refactoringflowbackend.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }
}
