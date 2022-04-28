package com.refactoryflow.refactoryflowbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
@JsonIgnoreProperties(value = "challengesInProgress")
public class Student {

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
    @ManyToMany(cascade =  {CascadeType.ALL})
    @JoinTable(
            name = "student_challenge",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "challenge_id")}
    )
    private List<Challenge> challengesInProgress;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "student_lo",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "lo_id")}
    )
    private List<LearningOutcomes> learningOutcomes;
}
