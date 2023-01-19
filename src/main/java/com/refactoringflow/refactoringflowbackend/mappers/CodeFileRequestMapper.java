package com.refactoringflow.refactoringflowbackend.mappers;

import com.refactoringflow.refactoringflowbackend.exchanges.CodeFileRequest;
import com.refactoringflow.refactoringflowbackend.model.assignment.Assignment;
import com.refactoringflow.refactoringflowbackend.model.assignment.AssignmentDTO;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class CodeFileRequestMapper implements EntityMapper<CodeFileRequest, CodeFile> {
    @Override
    public List<CodeFileRequest> toDtos(List<CodeFile> items) {
        return null;
    }

    @Override
    public CodeFileRequest toDto(CodeFile item) {
        return null;
    }

    @Override
    public CodeFile toEntity(CodeFileRequest codeFileRequest) {
        return new CodeFile( codeFileRequest.version + 1, "text/x-java-source", codeFileRequest.code.getBytes(StandardCharsets.UTF_8));
    }
}
