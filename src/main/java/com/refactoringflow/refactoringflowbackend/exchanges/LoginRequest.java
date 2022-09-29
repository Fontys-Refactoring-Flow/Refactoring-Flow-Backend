package com.refactoringflow.refactoringflowbackend.exchanges;

public class LoginRequest {
    public String name;
    public String password;

    public LoginRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
