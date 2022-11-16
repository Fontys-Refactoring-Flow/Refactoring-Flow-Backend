package com.refactoringflow.refactoringflowbackend.model.assignment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
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
@JsonIgnoreProperties({"codeFiles","students"})
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "refactoring_type")
    @NonNull
    private String refactoringType;
    @Column(name = "level")
    @NonNull
    private Long level;
    @Column(name = "description")
    @NonNull
    private String description;
    @Column(name = "risks")
    @NonNull
    private String risks;
    @Column(name = "language")
    @NonNull
    private String language;
}
