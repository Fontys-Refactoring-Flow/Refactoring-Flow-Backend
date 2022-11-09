package com.refactoringflow.refactoringflowbackend.model.assignment;

import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFileDTO;
import com.refactoringflow.refactoringflowbackend.model.user.StudentDTO;

import java.util.List;

public record AssignmentDTO (
        Long id,
        String refactoringType,
        Long level,
        List<StudentDTO> students,
        List<CodeFileDTO> codeFiles,
        String language
) { }
