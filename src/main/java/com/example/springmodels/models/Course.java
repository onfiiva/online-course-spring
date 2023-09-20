package com.example.springmodels.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer lessonsCount;
    private Integer cost;
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coursetypeId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CourseType courseType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coursedurationId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CourseDuration courseDuration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coursepaymentId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CoursePayment coursePayment;

    public Course(Long id, String name, Integer lessonsCount, Integer cost, String image, CourseType courseType, CourseDuration courseDuration, CoursePayment coursePayment) {
        this.id = id;
        this.name = name;
        this.lessonsCount = lessonsCount;
        this.cost = cost;
        this.image = image;
        this.courseType = courseType;
        this.courseDuration = courseDuration;
        this.coursePayment = coursePayment;
    }

    public Course() {

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

    public Integer getLessonsCount() {
        return lessonsCount;
    }

    public void setLessonsCount(Integer lessonsCount) {
        this.lessonsCount = lessonsCount;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public CourseDuration getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(CourseDuration courseDuration) {
        this.courseDuration = courseDuration;
    }

    public CoursePayment getCoursePayment() {
        return coursePayment;
    }

    public void setCoursePayment(CoursePayment coursePayment) {
        this.coursePayment = coursePayment;
    }
}
