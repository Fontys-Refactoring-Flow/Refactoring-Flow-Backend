package com.refactoryflow.refactoryflowbackend.Model;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student studentProgress;
    @Column(name = "code_quality")
    private long codeQuality;
    @Column(name = "refactoring")
    private long refactoring;
}
