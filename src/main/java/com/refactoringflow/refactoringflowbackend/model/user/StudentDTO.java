package com.refactoringflow.refactoringflowbackend.model.user;

public class StudentDTO extends UserDTO {
    public final Long semester;

    public StudentDTO(Long id, String name, String email, RoleDTO[] roles, Long semester) {
        super(id, name, email, roles);
        this.semester = semester;
    }
}
