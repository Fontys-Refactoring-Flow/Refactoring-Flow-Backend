package com.refactoringflow.refactoringflowbackend.model.user;

public class StudentPasswordDTO extends StudentDTO {
    public String password;

    public StudentPasswordDTO(Long id, String username, String email, String password, String[] authorities, Long semester) {
        super(id, username, email, authorities, semester);
        this.password = password;
    }
}

