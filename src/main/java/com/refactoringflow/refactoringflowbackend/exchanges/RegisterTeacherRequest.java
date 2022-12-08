package com.refactoringflow.refactoringflowbackend.exchanges;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterTeacherRequest {
    public String name;
    public String email;
    public String password;
    public String profile;
}
