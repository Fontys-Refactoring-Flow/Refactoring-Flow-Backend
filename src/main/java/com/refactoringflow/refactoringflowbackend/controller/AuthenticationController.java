package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.config.SecurityConfig;
import com.refactoringflow.refactoringflowbackend.error.exceptions.RefreshTokenException;
import com.refactoringflow.refactoringflowbackend.exchanges.*;
import com.refactoringflow.refactoringflowbackend.model.RefreshToken;
import com.refactoringflow.refactoringflowbackend.model.Role;
import com.refactoringflow.refactoringflowbackend.model.Student;
import com.refactoringflow.refactoringflowbackend.model.User;
import com.refactoringflow.refactoringflowbackend.service.JwtTokenService;
import com.refactoringflow.refactoringflowbackend.service.RefreshTokenService;
import com.refactoringflow.refactoringflowbackend.service.RoleService;
import com.refactoringflow.refactoringflowbackend.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
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
    private final RoleService roleService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(JwtTokenService jwtProvider,
                                    StudentService studentService,
                                    RoleService roleService,
                                    PasswordEncoder passwordEncoder,
                                    RefreshTokenService refreshTokenService) {
        this.jwtProvider = jwtProvider;
        this.studentService = studentService;
        this.roleService = roleService;
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
        Optional<Student> student = studentService.findByName(loginRequest.name);

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
        String jwt = jwtProvider.createJwtForClaims(loginRequest.name, claims, authorities,
                SecurityConfig.AUTHORITIES_CLAIM_NAME);

        return new ResponseEntity<>(new LoginResponse(student.get().getId(), loginRequest.name, student.get().getEmail(),
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
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("STUDENT"));

        if(studentService.findByName(registerRequest.name).isPresent() ||
                studentService.findByEmail(registerRequest.email).isPresent())
            return new ResponseEntity<>(
                    new ErrorResponse(HttpStatus.UNAUTHORIZED, "Student with that name/email already exists"),
                HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(register(
                registerRequest.name,
                registerRequest.email,
                registerRequest.password,
                registerRequest.semester,
                new HashSet<>(roles)), HttpStatus.OK);
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

        User user = token.get().getUser();

        List<String> authorities = user.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        claims.put("studentId", user.getId().toString());
        String jwt = jwtProvider.createJwtForClaims(user.getName(), claims, authorities, SecurityConfig.AUTHORITIES_CLAIM_NAME);
        return new RefreshResponse(jwt, refreshTokenService.generateRefreshToken(user.getId()).getToken(), "Bearer");
    }

    /**
     * Register a student with a specific role.
     * @param name The name
     * @param password The password
     * @param email The email
     * @param roleList The roles
     * @return The response with the student's information and a JWT token
     */
    @PostMapping("/admin/register")
    @PreAuthorize("hasRole('ADMIN')")
    public LoginResponse registerWithAuthorities(@RequestParam String name,
                                                 @RequestParam String password,
                                                 @RequestParam String email,
                                                 @RequestParam Long semester,
                                                 @RequestParam Collection<String> roleList) {
        List<Role> roles = roleList
                .stream()
                .map(roleService::findByName)
                .collect(Collectors.toList());

        if(roles.isEmpty() || !roles.contains(roleService.findByName("STUDENT")))
            roles.add(roleService.findByName("STUDENT"));

        return register(name, email, password, semester, new HashSet<>(roles));
    }

    @PostMapping("/admin/setauthorities")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> setRoles(@RequestParam String name,
                                                  @RequestBody Collection<String> roleList) {
        List<Role> roles = roleList
                .stream()
                .map(roleService::findByName)
                .collect(Collectors.toList());

        if(roles.isEmpty() || !roles.contains(roleService.findByName("STUDENT")))
            roles.add(roleService.findByName("STUDENT"));

        Optional<Student> student = studentService.findByName(name);
        if(student.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "Student not found"), HttpStatus.NOT_FOUND);
        } else {
            student.get().setRoles(new HashSet<>(roles));
            studentService.save(student.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private LoginResponse register(String name, String email, String password, Long semester, HashSet<Role> roles) {
        Student student = new Student(name,
                email,
                passwordEncoder.encode(password),
                semester,
                new ArrayList<>(),
                roles);

        studentService.save(student);

        String jwt = jwtProvider.createJwtForUser(student);
        return new LoginResponse(student.getId(), name, email,
                student.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new), jwt,
                refreshTokenService.generateRefreshToken(student.getId()).getToken(), "Bearer");
    }
}
