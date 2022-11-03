package com.refactoringflow.refactoringflowbackend.model.user;

public class TeacherPasswordDTO extends TeacherDTO {
    public final String password;

    public TeacherPasswordDTO(Long id, String username, String email, String[] authorities, String profile, String password) {
        super(id, username, email, authorities, profile);
        this.password = password;
    }
}
