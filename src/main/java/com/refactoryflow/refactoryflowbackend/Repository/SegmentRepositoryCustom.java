package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Course;
import com.refactoryflow.refactoryflowbackend.Model.Segment;

import java.util.List;
import java.util.Optional;


public interface SegmentRepositoryCustom {
    Optional<Segment> findByCourseId(long CourseId);
    Segment findByNrAndCourseId(Long Nr, Course course);
}
