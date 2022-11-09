package com.refactoringflow.refactoringflowbackend.model.user;

public class RefreshTokenDTO {
    public String refreshToken;
    public String accessToken;
    public String tokenType;

    public RefreshTokenDTO(String refreshToken, String accessToken, String tokenType) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
}
