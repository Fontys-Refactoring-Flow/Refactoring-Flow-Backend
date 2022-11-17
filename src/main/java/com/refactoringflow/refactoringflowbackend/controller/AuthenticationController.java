package com.refactoringflow.refactoringflowbackend.controller;

import com.refactoringflow.refactoringflowbackend.config.SecurityConfig;
import com.refactoringflow.refactoringflowbackend.error.exceptions.RefreshTokenException;
import com.refactoringflow.refactoringflowbackend.exchanges.*;
import com.refactoringflow.refactoringflowbackend.model.user.*;
import com.refactoringflow.refactoringflowbackend.service.*;
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
    private final UserService userService;
    private final RoleService roleService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(JwtTokenService jwtProvider,
                                    UserService userService,
                                    RoleService roleService,
                                    PasswordEncoder passwordEncoder,
                                    RefreshTokenService refreshTokenService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    /**
     * Authenticate a student and return the student's information and a JWT token.
     * @param loginRequest The login request
     * @return The response with the student's information and a JWT token
     */
    @PostMapping(value = "/student/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> loginStudent(LoginRequest loginRequest) {
        Map<String, String> claims = new HashMap<>();

        return loginUser(loginRequest, claims, SecurityConfig.ROLE_STUDENT);
    }

    /**
     * Authenticate a teacher and return the teacher's information and a JWT token.
     * @param loginRequest The login request
     * @return The response with the teacher's information and a JWT token
     */
    @PostMapping(value = "/teacher/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> loginTeacher(LoginRequest loginRequest) {
        Map<String, String> claims = new HashMap<>();

        return loginUser(loginRequest, claims, SecurityConfig.ROLE_TEACHER);
    }

    /**
    * Authenticate a user and return the user's information and a JWT token.
    * @param loginRequest The login request
    * @param claims The claims to add to the JWT token
    * @return The response with the user's information and a JWT token
    */
    private ResponseEntity<?> loginUser(LoginRequest loginRequest, Map<String, String> claims, Role role) {
        Optional<User> user = userService.findByEmail(loginRequest.email);

        if(user.isEmpty()) return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED, "User not found"),
                HttpStatus.UNAUTHORIZED);

        if(user.get().getRoles().stream().noneMatch((item) -> item.getName().equals(role.getName()))) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED, "Not allowed to login as this role"),
                    HttpStatus.UNAUTHORIZED);
        }

        if(!passwordEncoder.matches(loginRequest.password, user.get().getPassword()))
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED, "Wrong password given"),
                    HttpStatus.UNAUTHORIZED);

        List<String> authorities = user.get().getAuthorities()
                .stream().
                map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String jwt = jwtProvider.createJwtForClaims(user.get().getId(), claims, authorities,
                SecurityConfig.AUTHORITIES_CLAIM_NAME);

        return new ResponseEntity<>(new LoginResponse(user.get().getId(), user.get().getName(), user.get().getEmail(),
                authorities.toArray(new String[0]), jwt,
                refreshTokenService.generateRefreshToken(user.get().getId()).getToken(), "Bearer"), HttpStatus.OK);
    }

    /**
     * Register a student.
     * @param studentPasswordDTO The register request
     * @return The response with the student's information and a JWT token
     */

    @PostMapping(value = "/student/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> registerStudent(StudentPasswordDTO studentPasswordDTO) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("STUDENT"));

        if(userService.findByName(studentPasswordDTO.name).isPresent() ||
                userService.findByEmail(studentPasswordDTO.email).isPresent())
            return new ResponseEntity<>(
                    new ErrorResponse(HttpStatus.UNAUTHORIZED, "Student with that name/email already exists"),
                HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(registerStudent(
                studentPasswordDTO.name,
                studentPasswordDTO.email,
                studentPasswordDTO.password,
                studentPasswordDTO.semester,
                new HashSet<>(roles)), HttpStatus.OK);
    }

    /**
     * Refresh a student's JWT token using a refresh token.
     * @param refreshRequest The refresh request
     * @return The response with the refreshed JWT token and a new refresh token.
     */
    @PostMapping(value = "/refresh")
    public RefreshTokenDTO refresh(@RequestBody RefreshRequest refreshRequest) {
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

        String jwt = jwtProvider.createJwtForClaims(user.getId(), claims, authorities, SecurityConfig.AUTHORITIES_CLAIM_NAME);
        return new RefreshTokenDTO(jwt, refreshTokenService.generateRefreshToken(user.getId()).getToken(), "Bearer");
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

        return registerStudent(name, email, password, semester, new HashSet<>(roles));
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

        Optional<User> user = userService.findByName(name);
        if(user.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "User not found"), HttpStatus.NOT_FOUND);
        } else {
            user.get().setRoles(new HashSet<>(roles));
            userService.save(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private LoginResponse registerStudent(String name, String email, String password, Long semester, HashSet<Role> roles) {
        Student student = new Student(name,
                email,
                passwordEncoder.encode(password),
                semester,
                new ArrayList<>(),
                roles);

        userService.save(student);

        String jwt = jwtProvider.createJwtForUser(student);
        return new LoginResponse(student.getId(), name, email,
                student.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new), jwt,
                refreshTokenService.generateRefreshToken(student.getId()).getToken(), "Bearer");
    }
}
