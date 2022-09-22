package com.refactoringflow.refactoringflowbackend.Controller;

import com.refactoringflow.refactoringflowbackend.Model.Course;
import com.refactoringflow.refactoringflowbackend.Model.Segment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.refactoringflow.refactoringflowbackend.Service.SegmentService;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class SegmentController {

    SegmentService segmentService;

    @Autowired
    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @GetMapping(value = "/segment")
    public List<Segment> GetAllSegments(){
        return segmentService.findAll();
    }

    @GetMapping(value = "/segment/NrCourseID/{nr}/{courseid}")
    public Segment findByNrAndCourseId(@PathVariable Long nr, @PathVariable Long courseid){
        Course course = new Course();
        course.setID(courseid);
        return segmentService.findByNrAndCourseId(nr, course);
    }

    @GetMapping(value = "/segment/courseid/{courseid}")
    public Optional<Segment> findByCourseId(@PathVariable long courseid){
        return segmentService.findByCourseId(courseid);
    }

    @GetMapping(value = "/segment/{segmentid}")
    public Optional<Segment> findByID(@PathVariable Long segmentid){
        return segmentService.findById(segmentid);
    }

    @GetMapping(value = "/segment/segementcount/{courseid}")
    public Long segmentCount(@PathVariable Long courseid){
        return segmentService.segmentCount(courseid);
    }
}
