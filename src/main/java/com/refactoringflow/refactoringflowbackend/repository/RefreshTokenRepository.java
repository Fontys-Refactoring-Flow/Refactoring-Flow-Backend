package com.refactoringflow.refactoringflowbackend.repository;

import com.refactoringflow.refactoringflowbackend.model.RefreshToken;
import com.refactoringflow.refactoringflowbackend.model.User;
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
     * @param user The user
     * @return The refresh token
     */
    Optional<RefreshToken> findByUser(@NonNull User user);

    /**
     * Find a refresh token by its token.
     *
     * @param token The token
     * @return The refresh token
     */
    Optional<RefreshToken> findByToken(String token);
}
