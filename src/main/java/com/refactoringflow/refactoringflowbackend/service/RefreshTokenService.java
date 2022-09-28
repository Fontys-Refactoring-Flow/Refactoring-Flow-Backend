package com.refactoringflow.refactoringflowbackend.service;

import com.refactoringflow.refactoringflowbackend.model.RefreshToken;
import com.refactoringflow.refactoringflowbackend.repository.RefreshTokenRepository;
import com.refactoringflow.refactoringflowbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${jwt.refreshToken.expirationInMillis}")
    private Long refreshTokenDurationInMillis;
    private final RefreshTokenRepository refreshTokenRepository;
    private final StudentRepository studentRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, StudentRepository studentRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.studentRepository = studentRepository;
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
     * Find a refresh token by its students ID.
     * @param studentId The student ID
     * @return The refresh token
     */
    public Optional<RefreshToken> getRefreshTokenByStudentId(Long studentId) {
        return refreshTokenRepository.findByStudentId(studentId);
    }

    /**
     * Create a new refresh token for the given student.
     * @param studentId The student's id
     * @return The refresh token
     */
    public RefreshToken generateRefreshToken(Long studentId) {
        refreshTokenRepository.findByStudentId(studentId).ifPresent(refreshTokenRepository::delete);
        RefreshToken refreshToken = new RefreshToken(
                studentRepository.findById(studentId).orElseThrow(),
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

    public void deleteRefreshToken(Long id) {
        refreshTokenRepository.deleteById(id);
    }
}
