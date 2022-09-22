package com.refactoringflow.refactoringflowbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
@JsonIgnoreProperties(value = {"assignmentsInProgress", "learningOutcomes"})
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "semester")
    private Long semester;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "student_assignment",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "assignment_id")}
    )
    private List<Assignment> assignmentsInProgress;
    @OneToOne(mappedBy = "student")
    private LearningOutcomes learningOutcomes;
}
