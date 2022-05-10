package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.Segment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.refactoryflow.refactoryflowbackend.Exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

import com.refactoryflow.refactoryflowbackend.Service.SegmentService;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class SegmentController {

    SegmentService segmentService;

    @Autowired
    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @GetMapping(value = "/Segment")
    public List<Segment> GetAllSegments(){
        return segmentService.findAll();
    }

    @GetMapping(value = "/Segment/{Nr}/{CourseId}")
    public Segment findByNrAndCourseId(@PathVariable Long Nr, @PathVariable Long CourseId){
        return segmentService.findByNrAndCourseId(Nr, CourseId);
    }

    @GetMapping(value = "/Segment/CourseId/{CourseId}")
    public Optional<Segment> findSegmentByCourseId(@PathVariable Long CourseId){
        return segmentService.findSegmentByCourseId(CourseId);
    }

    @GetMapping(value = "/Segment/segementCount/{CourseId}")
    public Long segmentCount(@PathVariable Long CourseId){
        return segmentService.segementCount(CourseId);
    }
}
