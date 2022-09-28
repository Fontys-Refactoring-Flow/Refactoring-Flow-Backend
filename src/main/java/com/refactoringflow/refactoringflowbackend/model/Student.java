package com.refactoringflow.refactoringflowbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "student")
@JsonIgnoreProperties(value = {"assignmentsInProgress", "learningOutcomes"})
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "email")
    @NonNull
    private String email;
    @Column(name = "password")
    @NonNull
    private String password;
    @Column(name = "semester")
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "student_authorities", joinColumns = @JoinColumn(name = "student_id"))
    @NonNull
    private Collection<GrantedAuthority> authorities;

    @OneToOne(mappedBy = "student")
    @Nullable
    private LearningOutcomes learningOutcomes;
}
