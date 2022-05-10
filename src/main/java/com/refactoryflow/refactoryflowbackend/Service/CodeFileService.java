package com.refactoryflow.refactoryflowbackend.Service;

import com.refactoryflow.refactoryflowbackend.Model.CodeFile;
import com.refactoryflow.refactoryflowbackend.Repository.CodeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CodeFileService {

    private CodeFileRepository codeFileRepository;

    @Autowired
    public CodeFileService(CodeFileRepository codeFileRepository) {
        this.codeFileRepository = codeFileRepository;
    }

    public CodeFile store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        CodeFile CodeFile = new CodeFile(fileName, file.getContentType(), file.getBytes());
        return codeFileRepository.save(CodeFile);
    }

    public CodeFile getFile(long id){
        return codeFileRepository.findById(id).orElseThrow();
    }

    public List<CodeFile> getAllCodeFiles(){
        return codeFileRepository.findAll();
    }

    public CodeFile postCodeFile(CodeFile codeFile){
        return codeFileRepository.save(codeFile);
    }
}
