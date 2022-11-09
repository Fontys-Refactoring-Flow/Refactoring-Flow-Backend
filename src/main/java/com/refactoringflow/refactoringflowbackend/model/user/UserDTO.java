package com.refactoringflow.refactoringflowbackend.model.user;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public abstract class UserDTO {
    public Long id;
    @NonNull
    public String name;
    @NonNull
    public String email;
    @NonNull
    public RoleDTO[] roles;
}
