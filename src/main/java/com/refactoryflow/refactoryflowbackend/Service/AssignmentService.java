package com.refactoryflow.refactoryflowbackend.Service;

import com.refactoryflow.refactoryflowbackend.Model.Assignment;
import com.refactoryflow.refactoryflowbackend.Model.Student;
import com.refactoryflow.refactoryflowbackend.Repository.AssignmentRepository;
import com.refactoryflow.refactoryflowbackend.Repository.AssignmentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService implements AssignmentRepositoryCustom {

    @Autowired
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

    @Override
    public List<Assignment> findChallengeByRefactoringType(String refactoringType) {
        return assignmentRepository.findChallengeByRefactoringType(refactoringType);
    }

    @Override
    public List<Assignment> findChallengeByStudents(Student student) {
        return assignmentRepository.findChallengeByStudents(student);
    }


}
