package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.repository.AssignmentRepository;
import com.refactoringflow.refactoringflowbackend.model.Assignment;
import com.refactoringflow.refactoringflowbackend.model.Student;
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

    public Optional<Assignment> findById(Long challengeId) {
        return assignmentRepository.findById(challengeId);
    }

    public List<Assignment> findAssignmentByRefactoringType(String refactoringType) {
        return assignmentRepository.findAssignmentByRefactoringType(refactoringType);
    }

    public List<Assignment> findChallengeByStudent(Student student) {
        return assignmentRepository.findByStudentsContaining(student);
    }


}
