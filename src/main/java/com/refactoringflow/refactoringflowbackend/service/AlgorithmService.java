package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.codefile.Step;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlgorithmService {
    String API_Rename( );
    String ExtractMethod();
    String RenameMethod();
    List<Step> generateSteps(CodeFile codeFile);
}
