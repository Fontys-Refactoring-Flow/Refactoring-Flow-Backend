package com.refactoryflow.refactoryflowbackend.Controller;

import com.refactoryflow.refactoryflowbackend.Model.Segment;
import com.refactoryflow.refactoryflowbackend.Repository.ChallengeRepository;
import com.refactoryflow.refactoryflowbackend.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.refactoryflow.refactoryflowbackend.Exception.ResourceNotFoundException;
import java.util.List;
import com.refactoryflow.refactoryflowbackend.Service.SegmentService;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class SegmentController {

    SegmentService SegmentService;

    @Autowired
    public SegmentController(SegmentService SegmentService) {
        this.SegmentService = SegmentService;
    }

    @GetMapping(value = "/Segment")
    public List<Segment> GetAllSegments(){
        return SegmentService.findAll();
    }

    @GetMapping(value = "/Course/id/{Courseid}")
    public Segment GetCourseById(@PathVariable long Courseid){
        return SegmentService.findById(Courseid)
                .orElseThrow(() -> new ResourceNotFoundException("Segment not exist with id :" + Courseid));
    }

    @GetMapping(value = "/course/Title/{title}")
    public List<Segment> getChallengeBySubject(@PathVariable Integer CourseID){
        return SegmentService.FindSegmentByCourseID(CourseID);
    }
}
