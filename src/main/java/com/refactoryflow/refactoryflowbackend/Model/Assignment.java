package com.refactoryflow.refactoryflowbackend.Model;

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

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "language")
    private String language;
    @Column(name = "difficulty")
    private String difficulty;
    @Column(name = "duration")
    private int duration;
    @ManyToMany(mappedBy = "assignmentsInProgress")
    private List<Student> students;

    public Assignment(long id) {
        Id = id;
    }
}
