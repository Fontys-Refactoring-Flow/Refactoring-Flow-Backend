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
@JsonIgnoreProperties(value = {"assignmentsInProgress"})
public class Student extends User {
    @NonNull
    private Long semester;
    @ManyToMany
    @JoinTable(
            name = "student_assignment_code_file",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "assignment_id")}
    )
    @NonNull
    private List<Assignment> assignmentsInProgress;

    @ManyToMany
    @JoinTable(
            name = "student_assignment_code_file",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "code_file_id")}
    )
    private List<CodeFile> codeFiles;


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
