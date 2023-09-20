package com.example.springmodels.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseSummary {
    private List<Course> content;

    public List<Course> getContent() {
        return content;
    }

    public void setContent(List<Course> content) {
        this.content = content;
    }
}
