package com.refactoringflow.refactoringflowbackend.exchanges;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterRequest {
    public String username;
    public String email;
    public String password;
    public Long semester;
}
