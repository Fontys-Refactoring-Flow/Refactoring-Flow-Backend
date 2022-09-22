package com.refactoringflow.refactoringflowbackend.Model;

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
@Table(name = "assignment")
@JsonIgnoreProperties(value = "students")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "refactoring_type")
    private String refactoringType;
    @Column(name = "level")
    private long Level;
    @ManyToMany(mappedBy = "assignmentsInProgress")
    private List<Student> students;
    @Column(name = "language")
    private String language;

    public Assignment(long id) {
        Id = id;
    }
}
