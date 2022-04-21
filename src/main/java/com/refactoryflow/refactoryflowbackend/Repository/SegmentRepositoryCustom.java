package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Segment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentRepositoryCustom {
    List<Segment> FindSegmentByCourseID(Integer CourseID);
}
