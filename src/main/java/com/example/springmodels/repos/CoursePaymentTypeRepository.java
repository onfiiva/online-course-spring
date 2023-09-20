package com.example.springmodels.repos;

import com.example.springmodels.models.CoursePaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursePaymentTypeRepository extends JpaRepository<CoursePaymentType, Long> {
}
