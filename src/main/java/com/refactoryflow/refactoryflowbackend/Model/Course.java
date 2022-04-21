package com.refactoryflow.refactoryflowbackend.Model;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "Title")
    private String Title;
}
