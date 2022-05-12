package com.refactoryflow.refactoryflowbackend.Service;

import com.refactoryflow.refactoryflowbackend.Model.Course;
import com.refactoryflow.refactoryflowbackend.Model.Segment;
import com.refactoryflow.refactoryflowbackend.Repository.SegmentRepository;
import com.refactoryflow.refactoryflowbackend.Repository.SegmentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SegmentService implements SegmentRepositoryCustom {

    @Autowired
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

    @Override
    public Optional<Segment> findByCourseId(long CourseId) {
        return segmentRepository.findByCourseId(CourseId);
    }

    @Override
    public Segment findByNrAndCourseId(Long Nr, Course course) {
        return segmentRepository.findByNrAndCourseId(Nr, course);
    }

    public Long segmentCount(long CourseId){
        Optional<Segment> segments = findByCourseId(CourseId);
        return segments.stream().count();
    }

}
