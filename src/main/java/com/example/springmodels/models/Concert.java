package com.example.springmodels.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Concert")
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    public Concert(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Concert() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
