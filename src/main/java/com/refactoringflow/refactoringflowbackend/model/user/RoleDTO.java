package com.refactoringflow.refactoringflowbackend.model.user;

public class RoleDTO {
    public final Long id;
    public final String name;

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
