package com.refactoryflow.refactoryflowbackend.Model;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "learning_outcomes")
public class LearningOutcomes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToMany(mappedBy = "learningOutcomes")
    private List<Student> students;
    @Column(name = "code_quality")
    private long codeQuality;
    @Column(name = "refactoring")
    private long refactoring;
}
