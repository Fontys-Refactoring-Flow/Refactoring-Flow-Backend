package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.user.RefreshToken;
import com.refactoringflow.refactoringflowbackend.repository.RefreshTokenRepository;
import com.refactoringflow.refactoringflowbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class RefreshTokenService {
    @Value("${jwt.refreshToken.expirationInMillis}")
    private Long refreshTokenDurationInMillis;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    /**
     * Find a refresh token by its corresponding JWT token.
     * @param token The JWT token
     * @return The refresh token
     */
    public Optional<RefreshToken> getRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    /**
     * Create a new refresh token for the given student.
     * @param userId The student's id
     * @return The refresh token
     */
    public RefreshToken generateRefreshToken(Long userId) {
        refreshTokenRepository.deleteRefreshTokensByUser(userRepository.findById(userId).orElseThrow());
        RefreshToken refreshToken = new RefreshToken(
                userRepository.findById(userId).orElseThrow(),
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(refreshTokenDurationInMillis));
        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * Check if the given refresh token is valid.
     * @param refreshToken The refresh token
     * @return True if valid, false otherwise
     */
    public boolean isRefreshTokenValid(RefreshToken refreshToken) {
        if(refreshToken.getExpiryDate().isAfter(Instant.now())) {
            return true;
        } else {
            refreshTokenRepository.delete(refreshToken);
            return false;
        }
    }
}
