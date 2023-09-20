package com.example.springmodels.models;

import javax.persistence.*;

@Entity
@Table(name = "CourseDuration")
public class CourseDuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String duration;

    public CourseDuration(Long id, String duration) {
        this.id = id;
        this.duration = duration;
    }

    public CourseDuration() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
