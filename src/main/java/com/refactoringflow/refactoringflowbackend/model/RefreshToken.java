package com.refactoringflow.refactoringflowbackend.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    @NonNull
    @Column(nullable = false, unique = true)
    private String token;
    @NonNull
    @Column(nullable = false)
    private Instant expiryDate;
}


