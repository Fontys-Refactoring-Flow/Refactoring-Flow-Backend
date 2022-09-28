package com.refactoringflow.refactoringflowbackend.exchanges;

import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationErrorResponse {
    public List<FieldError> fieldErrors;

    public void addFieldError(String field, String localizedErrorMessage) {
    }
}
