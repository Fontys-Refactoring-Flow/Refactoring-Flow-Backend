package com.refactoringflow.refactoringflowbackend.model.user;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@RequiredArgsConstructor
public abstract class UserDTO implements Serializable {
    public final Long id;
    @NonNull
    public final String name;
    @NonNull
    public final String email;
    @NonNull
    public final String[] authorities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO entity = (UserDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.email, entity.email) &&
                Arrays.equals(this.authorities, entity.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, Arrays.hashCode(authorities));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + name + ", " +
                "email = " + email + ", " +
                "authorities = " + Arrays.toString(authorities) + ")";
    }
}
