package com.example.springmodels.repos;

import com.example.springmodels.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert,Long> {
}
