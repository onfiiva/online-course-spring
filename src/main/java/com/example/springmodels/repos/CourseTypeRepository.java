package com.example.springmodels.repos;

import com.example.springmodels.models.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTypeRepository extends JpaRepository<CourseType, Long> {
}
