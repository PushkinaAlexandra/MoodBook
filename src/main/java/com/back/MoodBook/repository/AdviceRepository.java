package com.back.MoodBook.repository;

import com.back.MoodBook.entity.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdviceRepository extends JpaRepository<Advice, Long> {
    @Query("SELECT a FROM Advice a WHERE a.mood = :keyMood")
    List<Advice> findByMood(@Param("keyMood") String keyMood);
}
