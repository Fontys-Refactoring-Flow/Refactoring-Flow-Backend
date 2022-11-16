package com.refactoringflow.refactoringflowbackend.model.codefile;

import com.refactoringflow.refactoringflowbackend.model.assignment.Assignment;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
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
    private Long id;

    @NonNull
    private int version;
    @NonNull
    private String name;
    @NonNull
    private String type;
    @Lob
    @NonNull
    private byte[] data;
}
