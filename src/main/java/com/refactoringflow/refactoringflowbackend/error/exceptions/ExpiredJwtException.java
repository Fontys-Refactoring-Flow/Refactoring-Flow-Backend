package com.refactoringflow.refactoringflowbackend.error.exceptions;

import org.springframework.security.oauth2.jwt.JwtException;

public class ExpiredJwtException extends JwtException {
    private final String msg;

    public ExpiredJwtException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
