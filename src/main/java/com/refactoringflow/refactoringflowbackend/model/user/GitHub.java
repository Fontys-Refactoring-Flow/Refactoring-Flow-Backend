package com.refactoringflow.refactoringflowbackend.model.user;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "github")
public class GitHub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Nullable
    private Long id;
    @NonNull
    private String accessToken;
}
