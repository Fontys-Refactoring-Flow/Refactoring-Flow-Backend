package com.refactoryflow.refactoryflowbackend.Service;

import com.refactoryflow.refactoryflowbackend.Model.Segment;
import com.refactoryflow.refactoryflowbackend.Repository.SegmentRepository;
import com.refactoryflow.refactoryflowbackend.Repository.SegmentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Segment> findById(long Segmentid) {
        return segmentRepository.findById(Segmentid);
    }

    @Override
    public List<Segment> FindSegmentByCourseID(Integer CourseID) {
        return segmentRepository.FindSegmentByCourseID(CourseID);
    }
}
