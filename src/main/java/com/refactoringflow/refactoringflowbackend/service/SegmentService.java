package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.repository.SegmentRepository;
import com.refactoringflow.refactoringflowbackend.model.Course;
import com.refactoringflow.refactoringflowbackend.model.Segment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SegmentService {

    private final SegmentRepository segmentRepository;

    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public List<Segment> findAll() {
        return segmentRepository.findAll();
    }

    public Optional<Segment> findById(long SegmentId) {
        return segmentRepository.findById(SegmentId);
    }

    public Optional<Segment> findByCourse(Course course) {
        return segmentRepository.findByCourse(course);
    }

    public Segment findByNrAndCourseId(Long Nr, Course course) {
        return segmentRepository.findByNrAndCourse(Nr, course);
    }

    public Long segmentCount(Course course){
        Optional<Segment> segments = findByCourse(course);
        return segments.stream().count();
    }

}
