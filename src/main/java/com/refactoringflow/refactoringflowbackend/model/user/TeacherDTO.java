package com.refactoringflow.refactoringflowbackend.model.user;

public class TeacherDTO extends UserDTO {
    public final String profile;

    public TeacherDTO(Long id, String username, String email, String[] authorities, String profile) {
        super(id, username, email, authorities);
        this.profile = profile;
    }
}
