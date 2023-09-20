package com.example.springmodels.models;

import javax.persistence.*;

@Entity
@Table(name = "UserRoles")
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public UserRoles(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserRoles() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
