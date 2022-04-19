package com.refactoryflow.refactoryflowbackend.Model;

import javax.persistence.*;

@Entity
@Table(name = "challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "name")
    private String Name;
    @Column(name = "description")
    private String Description;
    @Column(name = "subject")
    private String subject;
    @Column(name = "difficulty")
    private String Difficulty;
    @Column(name = "duration")
    private int Duration;

    public long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public Challenge(long id, String name, String subject, String difficulty, int duration) {
        Id = id;
        Name = name;
        this.subject = subject;
        Difficulty = difficulty;
        Duration = duration;
    }

    public Challenge() {
    }
}
