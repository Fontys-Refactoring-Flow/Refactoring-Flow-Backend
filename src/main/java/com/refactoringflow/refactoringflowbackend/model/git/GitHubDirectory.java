package com.refactoringflow.refactoringflowbackend.model.git;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class GitHubDirectory {
    @NonNull
    public String name;
    @NonNull
    public String path;
    public ArrayList<Object> files = new ArrayList<>();
}
