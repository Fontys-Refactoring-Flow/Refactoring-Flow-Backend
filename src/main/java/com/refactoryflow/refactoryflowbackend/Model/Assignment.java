package com.refactoryflow.refactoryflowbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignment")
@JsonIgnoreProperties(value = "users")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "refactoring_type")
    private String refactoringType;
    @Column(name = "level")
    private long level;
    @OneToOne(targetEntity = CodeFile.class, optional = true)
    private CodeFile codeFile;
    @ManyToMany(mappedBy = "assignmentsInProgress")
    private List<User> users;

    public Assignment(long id) {
        Id = id;
    }
}
