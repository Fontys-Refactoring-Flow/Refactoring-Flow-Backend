package com.refactoringflow.refactoringflowbackend.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CodeFile")
public class CodeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String Name;
    private String Type;

    @Lob
    private byte[] Data;

    public CodeFile(String name, String type, byte[] data) {
        Name = name;
        Type = type;
        Data = data;
    }
}
