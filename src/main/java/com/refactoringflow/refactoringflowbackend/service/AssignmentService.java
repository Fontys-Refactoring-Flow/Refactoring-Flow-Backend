package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.repository.AssignmentRepository;
import com.refactoringflow.refactoringflowbackend.model.assignment.Assignment;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> findById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId);
    }

    public List<Assignment> findAssignmentByStudent(Student student) {
        return assignmentRepository.findByStudentsContaining(student);
    }


}
