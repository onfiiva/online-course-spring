package com.example.springmodels.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
@Table(name = "modelUser")
public class modelUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String surname;
    private String name;
    private String patronymic;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserRoles role;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    public modelUser(){}
    public modelUser(Long id,
                     String username,
                     String password,
                     String surname,
                     String name,
                     String patronymic,
                     UserRoles role,
                     Course course) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.role = role;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
