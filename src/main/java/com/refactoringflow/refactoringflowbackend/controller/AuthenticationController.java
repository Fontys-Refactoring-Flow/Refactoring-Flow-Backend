package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.config.SecurityConfig;
import com.refactoringflow.refactoringflowbackend.error.exceptions.RefreshTokenException;
import com.refactoringflow.refactoringflowbackend.exchanges.*;
import com.refactoringflow.refactoringflowbackend.model.RefreshToken;
import com.refactoringflow.refactoringflowbackend.model.Student;
import com.refactoringflow.refactoringflowbackend.service.JwtTokenService;
import com.refactoringflow.refactoringflowbackend.service.RefreshTokenService;
import com.refactoringflow.refactoringflowbackend.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
    private final JwtTokenService jwtProvider;
    private final StudentService studentService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(JwtTokenService jwtProvider,
                                    StudentService studentService,
                                    PasswordEncoder passwordEncoder,
                                    RefreshTokenService refreshTokenService) {
        this.jwtProvider = jwtProvider;
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    /**
     * Authenticate a student and return the student's information and a JWT token.
     * @param loginRequest The login request
     * @return The response with the student's information and a JWT token
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Map<String, String> claims = new HashMap<>();
        Optional<Student> student = studentService.findByName(loginRequest.username);

        if(student.isEmpty()) return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED, "Student not found"),
                HttpStatus.UNAUTHORIZED);

        if(!passwordEncoder.matches(loginRequest.password, student.get().getPassword()))
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED, "Wrong password given"),
                    HttpStatus.UNAUTHORIZED);

        List<String> authorities = student.get().getAuthorities()
                .stream().
                map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("userId", student.get().getId().toString());
        String jwt = jwtProvider.createJwtForClaims(loginRequest.username, claims, authorities,
                SecurityConfig.AUTHORITIES_CLAIM_NAME);

        return new ResponseEntity<>(new LoginResponse(student.get().getId(), loginRequest.username, student.get().getEmail(),
                authorities.toArray(new String[0]), jwt,
                refreshTokenService.generateRefreshToken(student.get().getId()).getToken(), "Bearer"), HttpStatus.OK);
    }

    /**
     * Register a student.
     * @param registerRequest The register request
     * @return The response with the student's information and a JWT token
     */

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("STUDENT"));

        if(studentService.findByName(registerRequest.username).isPresent() ||
                studentService.findByEmail(registerRequest.email).isPresent())
            return new ResponseEntity<>(
                    new ErrorResponse(HttpStatus.UNAUTHORIZED, "Student with that username/email already exists"),
                HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(register(
                registerRequest.username,
                registerRequest.email,
                registerRequest.password,
                registerRequest.semester,
                authorityList), HttpStatus.OK);
    }

    /**
     * Refresh a student's JWT token using a refresh token.
     * @param refreshRequest The refresh request
     * @return The response with the refreshed JWT token and a new refresh token.
     */
    @PostMapping(value = "/refresh")
    public RefreshResponse refresh(@RequestBody RefreshRequest refreshRequest) {
        Map<String, String> claims = new HashMap<>();
        Optional<RefreshToken> token = refreshTokenService.getRefreshToken(refreshRequest.refreshToken);

        if(token.isEmpty()) throw new RefreshTokenException("Refresh token not found");

        if(!refreshTokenService.isRefreshTokenValid(token.get()))
            throw new RefreshTokenException("Refresh token expired");

        Student student = token.get().getStudent();

        List<String> authorities = student.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        claims.put("studentId", student.getId().toString());
        String jwt = jwtProvider.createJwtForClaims(student.getName(), claims, authorities, SecurityConfig.AUTHORITIES_CLAIM_NAME);
        return new RefreshResponse(jwt, refreshTokenService.generateRefreshToken(student.getId()).getToken(), "Bearer");
    }

    /**
     * Register a student with a specific role.
     * @param name The name
     * @param password The password
     * @param email The email
     * @param authorities The authorities
     * @return The response with the student's information and a JWT token
     */
    @PostMapping("/admin/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public LoginResponse registerWithAuthorities(@RequestParam String name,
                                                 @RequestParam String password,
                                                 @RequestParam String email,
                                                 @RequestParam Long semester,
                                                 @RequestParam Collection<String> authorities) {
        List<GrantedAuthority> authorityList = authorities
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        if(authorityList.isEmpty() || !authorityList.contains(new SimpleGrantedAuthority("STUDENT")))
            authorityList.add(new SimpleGrantedAuthority("STUDENT"));

        return register(name, email, password, semester, authorityList);
    }

    @PostMapping("/admin/setauthorities")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> setAuthorities(@RequestParam String username,
                                                  @RequestBody Collection<String> authorities) {
        List<GrantedAuthority> authorityList = authorities
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        if(authorityList.isEmpty() || !authorityList.contains(new SimpleGrantedAuthority("STUDENT")))
            authorityList.add(new SimpleGrantedAuthority("STUDENT"));

        Optional<Student> student = studentService.findByName(username);
        if(student.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "Student not found"), HttpStatus.NOT_FOUND);
        } else {
            student.get().setAuthorities(authorityList);
            studentService.save(student.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private LoginResponse register(String name, String email, String password, Long semester, List<GrantedAuthority> authorityList) {
        Student student = new Student(name,
                email,
                passwordEncoder.encode(password),
                semester,
                new ArrayList<>(),
                authorityList);

        studentService.save(student);

        String jwt = jwtProvider.createJwtForUser(student);
        return new LoginResponse(student.getId(), name, email,
                student.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new), jwt,
                refreshTokenService.generateRefreshToken(student.getId()).getToken(), "Bearer");
    }
}
