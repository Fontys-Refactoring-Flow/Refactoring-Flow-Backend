package com.refactoringflow.refactoringflowbackend.model.git;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GitHubFile {
    public String name;
    public String path;
    public String type;
    public String content;
    public String encoding;
}
