package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.AssignmentCodeFileStudent;
import com.refactoringflow.refactoringflowbackend.repository.AssigmentCodeFileStudentRepository;
import com.refactoringflow.refactoringflowbackend.repository.AssignmentRepository;
import com.refactoringflow.refactoringflowbackend.model.assignment.Assignment;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             AssigmentCodeFileStudentRepository assigmentCodeFileStudentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assigmentCodeFileStudentRepository = assigmentCodeFileStudentRepository;
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> findById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId);
    }

    public List<Assignment> findAssignmentByStudent(Student student) {
        List<Assignment> assignments = new ArrayList<>();

        for (AssignmentCodeFileStudent assignmentCodeFileStudent:
            assigmentCodeFileStudentRepository.findAssignmentCodeFilesStudentByStudent(student)) {
            assignments.add(assignmentCodeFileStudent.getAssignment());
        }

        return assignments;
    }


}
