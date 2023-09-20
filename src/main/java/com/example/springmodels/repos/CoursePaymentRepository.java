package com.example.springmodels.repos;

import com.example.springmodels.models.CoursePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursePaymentRepository extends JpaRepository<CoursePayment, Long> {
    List<CoursePayment> findByTypeId(Long typeId);
}
