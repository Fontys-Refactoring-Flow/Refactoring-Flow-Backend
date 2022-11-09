package com.refactoringflow.refactoringflowbackend.exchanges;

import com.refactoringflow.refactoringflowbackend.model.user.StudentPasswordDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterStudentRequest {
    public StudentPasswordDTO studentPasswordDTO;
}
