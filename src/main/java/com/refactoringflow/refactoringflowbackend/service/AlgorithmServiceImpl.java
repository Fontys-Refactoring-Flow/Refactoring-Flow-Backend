package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.codefile.Step;
import com.refactoringflow.refactoringflowbackend.repository.StepRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    private final StepRepository stepRepository;

    public AlgorithmServiceImpl(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
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
        List<Step> steps = List.of(
                new Step(1, codeFile, "Step 1", "Description 1"),
                new Step(2, codeFile, "Step 2", "Description 2"),
                new Step(3, codeFile, "Step 3", "Description 3")
        );

        stepRepository.saveAll(steps);

        return steps;
    }
}
