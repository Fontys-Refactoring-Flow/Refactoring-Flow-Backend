package com.refactoryflow.refactoryflowbackend.Service;

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
    public Optional<Segment> findSegmentByCourseId(Long CourseId) {
        return segmentRepository.findSegmentByCourseId(CourseId);
    }

    @Override
    public Segment findByNrAndCourseId(Long Nr, Long CourseId) {
        return findByNrAndCourseId(Nr, CourseId);
    }

    public Long segementCount(Long CourseId){
        Optional<Segment> segments = findSegmentByCourseId(CourseId);
        Long count = segments.stream().count();
        return count;
    }

}
