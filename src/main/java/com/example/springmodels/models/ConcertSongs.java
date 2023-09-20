package com.example.springmodels.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "ConcertSongs")
public class ConcertSongs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "concertId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Concert concert;

    @ManyToOne
    @JoinColumn(name = "studentsongId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private StudentSong studentSong;

    public ConcertSongs(Long id, Concert concert, StudentSong studentSong) {
        this.id = id;
        this.concert = concert;
        this.studentSong = studentSong;
    }

    public ConcertSongs() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public StudentSong getStudentSong() {
        return studentSong;
    }

    public void setStudentSong(StudentSong studentSong) {
        this.studentSong = studentSong;
    }
}
