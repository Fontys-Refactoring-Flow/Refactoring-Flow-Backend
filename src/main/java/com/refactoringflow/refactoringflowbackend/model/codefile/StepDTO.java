package com.refactoringflow.refactoringflowbackend.model.codefile;

public record StepDTO(Long id, Integer stepIndex, Long codeFileId, String name, String description) {
}
