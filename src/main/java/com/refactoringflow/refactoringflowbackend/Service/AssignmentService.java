package com.refactoringflow.refactoringflowbackend.Service;

import com.refactoringflow.refactoringflowbackend.Repository.AssignmentRepository;
import com.refactoringflow.refactoringflowbackend.Model.Assignment;
import com.refactoringflow.refactoringflowbackend.Model.Student;
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

    public Optional<Assignment> findById(long challengeid) {
        return assignmentRepository.findById(challengeid);
    }

    public List<Assignment> findChallengeByRefactoringType(String refactoringType) {
        return assignmentRepository.findAssignmentByRefactoringType(refactoringType);
    }

    public List<Assignment> findChallengeByStudents(Student student) {
        return assignmentRepository.findChallengeByStudents(student);
    }


}
