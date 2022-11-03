package com.refactoringflow.refactoringflowbackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.refactoringflow.refactoringflowbackend.config.SecurityConfig;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenService {
    @Value("${jwt.accessToken.expirationInMillis}")
    private Long accessTokenDurationInMillis;

    private final StudentService studentService;
    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public JwtTokenService(StudentService studentService, RSAPrivateKey privateKey, RSAPublicKey publicKey) {
        this.studentService = studentService;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    /**
     * Generate a new JWT token for the given student.
     *
     * @param student The student
     * @return The access token
     */
    public String createJwtForUser(Student student) {
        Map<String, String> claims = new HashMap<>();
        claims.put("userId", student.getId().toString());
        List<String> authorities = student.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return createJwtForClaims(student.getName(), claims, authorities, SecurityConfig.AUTHORITIES_CLAIM_NAME);
    }

    /**
     * Create a JWT for the given claims.
     *
     * @param subject The subject
     * @param claims  The claims
     * @return The generated JWT
     */
    public String createJwtForClaims(String subject, Map<String, String> claims, List<String> authorities, String authoritiesClaimName) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Instant.now().toEpochMilli());
        calendar.add(Calendar.DATE, 1);

        JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject)
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plusMillis(accessTokenDurationInMillis)));

        claims.forEach(jwtBuilder::withClaim);
        jwtBuilder.withArrayClaim(authoritiesClaimName, authorities.toArray(new String[0]));

        return jwtBuilder
                .withNotBefore(new Date())
                .withExpiresAt(Date.from(Instant.now().plusMillis(accessTokenDurationInMillis)))
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }

    /**
     * @param authToken The JWT token to validate.
     * @return True or false depending on whether the token is valid or not.
     * @throws JWTVerificationException If the token is invalid.
     */
    public boolean validateToken(String authToken) throws JWTVerificationException {
        DecodedJWT jwt = JWT.require(Algorithm.RSA256(publicKey, privateKey))
                .build()
                .verify(authToken);
        return jwt.getExpiresAt().after(new Date());
    }

    /**
     * Get a student by its corresponding JWT token.
     * @param token The JWT token
     * @return Student
     * @throws JwtException If the corresponding student does not exist
     */
    public Student getUserFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);

        long id = Long.parseLong(jwt.getClaims().get("id").toString().replaceAll("\"", ""));
        Optional<Student> student = studentService.findById(id);
        return student.orElseThrow(() -> new JwtException("Student not found for given JWT"));
    }
}