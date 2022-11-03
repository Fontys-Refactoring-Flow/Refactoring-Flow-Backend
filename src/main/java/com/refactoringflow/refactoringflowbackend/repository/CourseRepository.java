package com.refactoringflow.refactoringflowbackend.repository;

import com.refactoringflow.refactoringflowbackend.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}