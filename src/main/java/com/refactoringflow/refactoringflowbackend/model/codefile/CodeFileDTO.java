package com.refactoringflow.refactoringflowbackend.model.codefile;


public record CodeFileDTO(Long id, Integer version, String type, byte[] data) {
}
