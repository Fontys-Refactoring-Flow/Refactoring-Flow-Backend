package com.refactoringflow.refactoringflowbackend.mappers;

import com.refactoringflow.refactoringflowbackend.model.assignment.Assignment;
import com.refactoringflow.refactoringflowbackend.model.assignment.AssignmentDTO;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class AssignmentMapper implements EntityMapper<AssignmentDTO, Assignment> {
    @Override
    public List<AssignmentDTO> toDtos(List<Assignment> items) {
        List<AssignmentDTO> dtos = new ArrayList<>();
        items.forEach((item) -> dtos.add(toDto(item)));
        return dtos;
    }

    @Override
    public AssignmentDTO toDto(Assignment item) {
        return null;
    }

    @Override
    public Assignment toEntity(AssignmentDTO assignmentDTO) {
        return null;
    }
}
