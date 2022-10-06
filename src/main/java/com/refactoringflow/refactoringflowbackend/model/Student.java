package com.refactoringflow.refactoringflowbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student")
@JsonIgnoreProperties(value = {"assignmentsInProgress", "learningOutcomes"})
public class Student extends User {
    @NonNull
    private Long semester;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "student_assignment",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "assignment_id")}
    )
    @NonNull
    private List<Assignment> assignmentsInProgress;


    public Student(String name,
                   String email,
                   String password,
                   Long semester,
                   List<Assignment> assignmentsInProgress,
                   Set<Role> roles) {
        super(name, email, password, roles);
        this.semester = semester;
        this.assignmentsInProgress = assignmentsInProgress;
    }

    public Student() {
        super();
    }
}
