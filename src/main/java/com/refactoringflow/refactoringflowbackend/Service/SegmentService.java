package com.refactoringflow.refactoringflowbackend.Service;

import com.refactoringflow.refactoringflowbackend.Repository.SegmentRepository;
import com.refactoringflow.refactoringflowbackend.Model.Course;
import com.refactoringflow.refactoringflowbackend.Model.Segment;
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

    public Optional<Segment> findByCourseId(long CourseId) {
        return segmentRepository.findByCourseId(CourseId);
    }

    public Segment findByNrAndCourseId(Long Nr, Course course) {
        return segmentRepository.findByNrAndCourseId(Nr, course);
    }

    public Long segmentCount(long CourseId){
        Optional<Segment> segments = findByCourseId(CourseId);
        return segments.stream().count();
    }

}
