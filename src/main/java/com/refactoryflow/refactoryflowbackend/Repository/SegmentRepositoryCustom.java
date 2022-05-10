package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Segment;

import java.util.List;
import java.util.Optional;


public interface SegmentRepositoryCustom {
    Optional<Segment> findSegmentByCourseId(Long CourseId);
    Segment findByNrAndCourseId(Long Nr, Long CourseId);
}
