package com.refactoringflow.refactoringflowbackend.mappers;

import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFileDTO;

import java.util.ArrayList;
import java.util.List;

public class CodeFileMapper implements EntityMapper<CodeFileDTO, CodeFile> {
    @Override
    public List<CodeFileDTO> toDtos(List<CodeFile> items) {
        List<CodeFileDTO> dtos = new ArrayList<>();
        items.forEach((item) -> dtos.add(toDto(item)));
        return dtos;
    }

    @Override
    public CodeFileDTO toDto(CodeFile item) {
        return new CodeFileDTO(
            item.getId(),
            item.getVersion(),
            item.getName(),
            item.getType(),
            item.getData()
        );
    }

    @Override
    public CodeFile toEntity(CodeFileDTO codeFileDTO) {
        return new CodeFile(
            codeFileDTO.version(),
            codeFileDTO.name(),
            codeFileDTO.type(),
            codeFileDTO.data()
        );
    }
}
