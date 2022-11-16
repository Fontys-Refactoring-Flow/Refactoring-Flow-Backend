package com.refactoringflow.refactoringflowbackend.model;

import com.refactoringflow.refactoringflowbackend.model.assignment.Assignment;
import com.refactoringflow.refactoringflowbackend.model.codefile.CodeFile;
import com.refactoringflow.refactoringflowbackend.model.user.Student;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student_assignment_code_file")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssignmentCodeFileStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "code_file_id")
    private CodeFile codeFile;
}
