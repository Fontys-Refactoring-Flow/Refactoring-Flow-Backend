package com.refactoryflow.refactoryflowbackend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Segment")
public class Segment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CourseId")
    private Course courseId;

    @Column(name = "Title")
    private String title;
    @Column(name = "Text")
    private String text;
    @Column(name = "Nr")
    private Long nr;
}
