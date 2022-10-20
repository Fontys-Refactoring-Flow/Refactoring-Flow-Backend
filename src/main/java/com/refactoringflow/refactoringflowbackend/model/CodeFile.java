package com.refactoringflow.refactoringflowbackend.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "code_file")
public class CodeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private int version;
    @NonNull
    private String name;
    @NonNull
    private String type;
    @Lob
    @NonNull
    private byte[] data;
    @ManyToOne
    @JoinTable(
            name = "student_assignment_code_file",
            joinColumns = {@JoinColumn(name = "code_file_id")},
            inverseJoinColumns = {@JoinColumn(name = "assignment_id")}
    )
    private Assignment assignment;
}
