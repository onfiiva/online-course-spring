package com.example.springmodels.repos;

import com.example.springmodels.models.ConcertSongs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertSongsRepository extends JpaRepository<ConcertSongs, Long> {
    List<ConcertSongs> findByConcertIdAndStudentSongId(Long concertId, Long studentsongId);
}
