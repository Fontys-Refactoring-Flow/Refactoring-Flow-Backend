package com.refactoringflow.refactoringflowbackend.model.user;

public class StudentPasswordDTO extends StudentDTO {
    public String password;

    public StudentPasswordDTO(Long id, String name, String email, String password, RoleDTO[] roles, Long semester) {
        super(id, name, email, roles, semester);
        this.password = password;
    }
}

