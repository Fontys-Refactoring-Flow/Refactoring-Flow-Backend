package com.refactoringflow.refactoringflowbackend.model.codefile;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "step")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Integer stepIndex;
    @NonNull
    private String title;
    @NonNull
    private String description;
}
