package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.repository.CodeFileRepository;
import com.refactoringflow.refactoringflowbackend.model.CodeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class CodeFileService {

    private final CodeFileRepository codeFileRepository;

    @Autowired
    public CodeFileService(CodeFileRepository codeFileRepository) {
        this.codeFileRepository = codeFileRepository;
    }

    public void store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        CodeFile CodeFile = new CodeFile(fileName, file.getContentType(), file.getBytes());
        codeFileRepository.save(CodeFile);
    }

    public CodeFile getFile(long id){
        return codeFileRepository.findById(id).orElseThrow();
    }

    public Stream<CodeFile> getAllCodeFiles(){
        return codeFileRepository.findAll().stream();
    }

    public CodeFile postCodeFile(CodeFile codeFile){
        return codeFileRepository.save(codeFile);
    }
}
