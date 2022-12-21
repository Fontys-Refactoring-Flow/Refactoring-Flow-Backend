package com.refactoringflow.refactoringflowbackend.refactorAlgorithm;

import org.springframework.stereotype.Repository;

@Repository
public class DummyAlgorithm {
    public String API_Rename( ){
        return  "Below are INSTRUCTIONS for renaming int MethodXYZ() in class MyMethod of your project, assuming all risks are taken into account.\n" +
                "Feel free to skip specific steps for risks which you think are not relevant. Steps are marked with [] for specific risks.\n" +
                "\n" +
                "Rename int MethodXYZ() in class MyMethod to your new name\n" +
                "\n" +
                "Build project.\n" +
                "Resolve unresolved references to int MethodXYZ() indicated by compiler by changing the old name to the new name.\n" +
                "Run your automatic tests and solve issues.\n" +
                "\n";
    };

    public String ExtractMethod(){
        return  "Below are INSTRUCTIONS for extracting int MethodXYZ() in class MyMethod of your project, assuming all risks are taken into account.\n" +
                "Feel free to skip specific steps for risks which you think are not relevant. Steps are marked with [] for specific risks.\n" +
                "\n" +
                "Extract int MethodXYZ() in class MyMethod to your new class\n" +
                "\n" +
                "Build project.\n" +
                "Resolve unresolved references to int MethodXYZ() indicated by compiler by changing the old name to the new name.\n" +
                "Run your automatic tests and solve issues.\n" +
                "\n";
    };

    public String RenameMethod(){
        return  "Below are INSTRUCTIONS for renaming int MethodXYZ() in class MyMethod of your project, assuming all risks are taken into account.\n" +
                "Feel free to skip specific steps for risks which you think are not relevant. Steps are marked with [] for specific risks.\n" +
                "\n" +

                "Rename int MethodXYZ() in class MyMethod to your new name\n" +
                "\n" +
                "Build project.\n" +
                "Resolve unresolved references to int MethodXYZ() indicated by compiler by changing the old name to the new name.\n" +
                "Run your automatic tests and solve issues.\n" +
                "\n";
    };
}
