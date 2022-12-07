package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.refactorAlgorithm.DummyAlgorithm;

public class AlgorithmServiceImpl implements AlgorithmService{

    private final DummyAlgorithm dummyAlgorithm;

    public AlgorithmServiceImpl(DummyAlgorithm dummy){
        this.dummyAlgorithm = dummy;
    }

    @Override
    public String API_Rename( ){return dummyAlgorithm.API_Rename();}

    @Override
    public String ExtractMethod(){return dummyAlgorithm.ExtractMethod();}

    @Override
    public String RenameMethod(){return dummyAlgorithm.RenameMethod();}
}
