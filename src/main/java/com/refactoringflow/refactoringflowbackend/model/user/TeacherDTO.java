package com.refactoringflow.refactoringflowbackend.model.user;

public class TeacherDTO extends UserDTO {
    public final String profile;

    public TeacherDTO(Long id, String username, String email, RoleDTO[] roles, String profile) {
        super(id, username, email, roles);
        this.profile = profile;
    }
}
