package com.refactoringflow.refactoringflowbackend.exchanges;

public class RefreshResponse {
    public String accessToken;
    public String refreshToken;
    public String tokenType;

    public RefreshResponse(String accessToken, String refreshToken, String tokenType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }
}
