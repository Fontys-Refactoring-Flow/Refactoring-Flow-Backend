package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.codefile.Step;
import com.refactoringflow.refactoringflowbackend.repository.CodeFileRepository;
import com.refactoringflow.refactoringflowbackend.repository.StepRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    private final StepRepository stepRepository;
    private final CodeFileRepository codeFileRepository;

    public AlgorithmServiceImpl(StepRepository stepRepository, CodeFileRepository codeFileRepository) {
        this.stepRepository = stepRepository;
        this.codeFileRepository = codeFileRepository;
    }

    @Override
    public String API_Rename() {
        return null;
    }

    @Override
    public String ExtractMethod() {
        return null;
    }

    @Override
    public String RenameMethod() {
        return null;
    }

    @Override
    public List<Step> generateSteps(CodeFile codeFile) {
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(1, "Step 1", "Description 1"));
        steps.add(new Step(2, "Step 2", "Description 2"));
        steps.add(new Step(3, "Step 3", "Description 3"));

        if(!codeFile.getSteps().isEmpty()) {
            stepRepository.deleteAll(codeFile.getSteps());
            codeFile.getSteps().clear();
        }

        codeFile.setSteps(steps);
        codeFileRepository.save(codeFile);
        stepRepository.saveAll(steps);
        return steps;
    }
}
