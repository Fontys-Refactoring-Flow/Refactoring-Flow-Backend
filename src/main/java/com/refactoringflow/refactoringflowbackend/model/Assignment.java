package com.refactoringflow.refactoringflowbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "assignment")
@JsonIgnoreProperties(value = "students")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "refactoring_type")
    @NonNull
    private String refactoringType;
    @Column(name = "level")
    @NonNull
    private long Level;
    @OneToMany
    @JoinTable(
            name = "student_assignment_code_file",
            joinColumns = {@JoinColumn(name = "assignment_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    @NonNull
    private List<Student> students;
    @OneToMany
    @JoinTable(
            name = "student_assignment_code_file",
            joinColumns = {@JoinColumn(name = "assignment_id")},
            inverseJoinColumns = {@JoinColumn(name = "code_file_id")}
    )
    private List<CodeFile> codeFiles;
    @Column(name = "language")
    @NonNull
    private String language;
}
