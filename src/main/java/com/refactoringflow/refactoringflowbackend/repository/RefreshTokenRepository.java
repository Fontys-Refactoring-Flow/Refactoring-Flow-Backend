package com.refactoringflow.refactoringflowbackend.repository;

import com.refactoringflow.refactoringflowbackend.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    /**
     * Find a refresh token by its ID.
     *
     * @param id The id
     * @return The refresh token
     */
    @NonNull
    Optional<RefreshToken> findById(@NonNull Long id);

    /**
     * Find a refresh token by its students ID.
     *
     * @param studentId The students ID
     * @return The refresh token
     */
    Optional<RefreshToken> findByStudentId(@NonNull Long studentId);

    /**
     * Find a refresh token by its token.
     *
     * @param token The token
     * @return The refresh token
     */
    Optional<RefreshToken> findByToken(String token);
}
