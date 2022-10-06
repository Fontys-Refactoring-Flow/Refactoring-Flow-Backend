package com.refactoringflow.refactoringflowbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "teacher")
public class Teacher extends User {
    @Column(name = "profile")
    private String profile;

    public Teacher(String name,
                   String email,
                   String password,
                   String profile,
                   Set<Role> roles) {
        super(name, email, password, roles);
        this.profile = profile;
    }

    public Teacher() {
        super();
    }
}
