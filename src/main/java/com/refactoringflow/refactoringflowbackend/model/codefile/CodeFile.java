package com.refactoringflow.refactoringflowbackend.model.codefile;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

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
    private String type;
    @Lob
    @NonNull
    private byte[] data;
    private String feedback;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Step> steps;
}
