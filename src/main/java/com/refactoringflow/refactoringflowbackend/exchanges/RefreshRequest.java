package com.refactoringflow.refactoringflowbackend.exchanges;

public class RefreshRequest {
    public String refreshToken;

    public RefreshRequest() {
    }

    public RefreshRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
