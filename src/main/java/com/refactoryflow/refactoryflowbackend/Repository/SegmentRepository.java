package com.refactoryflow.refactoryflowbackend.Repository;


import com.refactoryflow.refactoryflowbackend.Model.Course;
import com.refactoryflow.refactoryflowbackend.Model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long> {
    Optional<Segment> findByCourseId(long CourseId);
    Segment findByNrAndCourseId(Long Nr, Course course);
}
