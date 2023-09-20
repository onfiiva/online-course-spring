package com.example.springmodels.repos;

import com.example.springmodels.models.StudentSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentSongRepository extends JpaRepository<StudentSong, Long> {
    List<StudentSong> findByUserId(Long userId);
}
