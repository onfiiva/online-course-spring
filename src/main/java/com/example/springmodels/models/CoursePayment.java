package com.example.springmodels.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "CoursePayment")
public class CoursePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double sum;

    @ManyToOne
    @JoinColumn(name = "typeId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CoursePaymentType type;

    public CoursePayment(Long id, Double sum, CoursePaymentType type) {
        this.id = id;
        this.sum = sum;
        this.type = type;
    }

    public CoursePayment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public CoursePaymentType getType() {
        return type;
    }

    public void setType(CoursePaymentType type) {
        this.type = type;
    }
}
