package com.refactoryflow.refactoryflowbackend.Model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.w3c.dom.Text;

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
    @JoinColumn(name = "Course_Id")
    private Course Course_Id;

    @Column(name = "Title")
    private String title;
    @Column(name = "Text")
    private String Text;
}
