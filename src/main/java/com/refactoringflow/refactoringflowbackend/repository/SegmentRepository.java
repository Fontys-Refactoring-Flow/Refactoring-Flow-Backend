package com.refactoringflow.refactoringflowbackend.repository;


import com.refactoringflow.refactoringflowbackend.model.Course;
import com.refactoringflow.refactoringflowbackend.model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long> {
    Optional<Segment> findByCourse(Course courseId);
    Segment findByNrAndCourse(Long nr, Course course);
}
