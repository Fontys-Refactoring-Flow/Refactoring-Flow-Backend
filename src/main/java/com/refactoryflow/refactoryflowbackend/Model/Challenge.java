package com.refactoryflow.refactoryflowbackend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "name")
    private String Name;
    @Column(name = "description")
    private String Description;
    @Column(name = "subject")
    private String subject;
    @Column(name = "difficulty")
    private String Difficulty;
    @Column(name = "duration")
    private int Duration;
    @ManyToMany(mappedBy = "challengesInProgress")
    private List<Student> students;
}
