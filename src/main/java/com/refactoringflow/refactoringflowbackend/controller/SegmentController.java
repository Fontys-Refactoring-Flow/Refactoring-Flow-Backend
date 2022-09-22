package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.model.Course;
import com.refactoringflow.refactoringflowbackend.model.Segment;
import com.refactoringflow.refactoringflowbackend.service.CourseService;
import com.refactoringflow.refactoringflowbackend.service.SegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/segment")
public class SegmentController {

    private  final SegmentService segmentService;
    private final CourseService courseService;

    @Autowired
    public SegmentController(SegmentService segmentService, CourseService courseService) {
        this.segmentService = segmentService;
        this.courseService = courseService;
    }

    @GetMapping(value = "/")
    public List<Segment> GetAllSegments(){
        return segmentService.findAll();
    }

    @GetMapping(value = "/findByNrCourseId/{nr}/{courseId}")
    public Segment findByNrAndCourseId(@PathVariable Long nr, @PathVariable Long courseId){
        Course course = new Course();
        course.setID(courseId);
        return segmentService.findByNrAndCourseId(nr, course);
    }

    @GetMapping(value = "/findByCourseId/{courseId}")
    public Optional<Segment> findByCourse(@PathVariable long courseId){
        Optional<Course> course = courseService.findById(courseId);
        if(course.isPresent()) {
            return segmentService.findByCourse(course.get());
        }

        return Optional.empty();
    }

    @GetMapping(value = "/findBySegmentId/{segmentId}")
    public Optional<Segment> findByID(@PathVariable Long segmentId){
        return segmentService.findById(segmentId);
    }

    @GetMapping(value = "/getSegmentCount/{courseId}")
    public Long segmentCount(@PathVariable Long courseId){
        Optional<Course> course = courseService.findById(courseId);
        if(course.isPresent()) {
            return segmentService.segmentCount(course.get());
        }

        return 0L;
    }
}
