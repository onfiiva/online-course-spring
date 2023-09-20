package com.example.springmodels.repos;

import com.example.springmodels.models.modelUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<modelUser,Long> {
    List<modelUser> findByRoleIdAndCourseId(Long roleId, Long courseId);

    modelUser findByUsername(String username);
}
