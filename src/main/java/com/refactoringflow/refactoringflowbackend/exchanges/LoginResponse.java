package com.refactoringflow.refactoringflowbackend.exchanges;

public class LoginResponse {
    public Long id;
    public String name;
    public String email;
    public String[] authorities;
    public String accessToken;
    public String refreshToken;
    public String tokenType;

    public LoginResponse(Long id, String name, String email,
                         String[] authorities, String accessToken,
                         String refreshToken, String tokenType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.authorities = authorities;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }
}
