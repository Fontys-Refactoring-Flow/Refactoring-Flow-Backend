package com.refactoringflow.refactoringflowbackend.error.exceptions;

public class RefreshTokenException extends RuntimeException {
    private final String msg;

    public RefreshTokenException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
