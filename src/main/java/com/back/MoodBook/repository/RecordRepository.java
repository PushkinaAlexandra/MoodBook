package com.back.MoodBook.repository;

import com.back.MoodBook.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query("SELECT r FROM Record r WHERE r.createdAt BETWEEN :startOfMonth AND :endOfMonth")
    List<Record> findByMonth(
            @Param("startOfMonth") LocalDateTime startOfMonth,
            @Param("endOfMonth") LocalDateTime endOfMonth);

    @Query("SELECT r FROM Record r WHERE r.createdAt <= :today AND r.createdAt >= :lastDay")
    List<Record> findLast30DaysRecords(
            @Param("today") LocalDateTime today,
            @Param("lastDay") LocalDateTime lastDay);
}
