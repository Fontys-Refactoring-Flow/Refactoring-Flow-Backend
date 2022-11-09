package com.refactoringflow.refactoringflowbackend.model.user;

public class StudentPasswordDTO extends StudentDTO {
    public String password;

    public StudentPasswordDTO(Long id, String username, String email, String password, RoleDTO[] roles, Long semester) {
        super(id, username, email, roles, semester);
        this.password = password;
    }
}

