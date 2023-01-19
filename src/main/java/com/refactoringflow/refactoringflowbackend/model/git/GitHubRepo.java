package com.refactoringflow.refactoringflowbackend.model.git;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GitHubRepo {
    public List<Object> files;
}
