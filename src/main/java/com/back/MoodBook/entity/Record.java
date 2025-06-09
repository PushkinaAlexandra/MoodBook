package com.back.MoodBook.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Date Time
    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    //Mood
    @NotBlank(message = "Choose Mood")
    private String mood;
    //Extra_Mood
    private String extraMood;
    //Reason
    private String reason;
    //Advice
//    @ManyToOne
//    @JoinColumn(name = "advice_id", unique = false)
//    @NaturalId(mutable = false)
//    public Advice advice;
    private Long adviceId;

//    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public @NotBlank(message = "Choose Mood") String getMood() {
        return mood;
    }

    public void setMood(@NotBlank(message = "Choose Mood") String mood) {
        this.mood = mood;
    }

    public String getExtraMood() {
        return extraMood;
    }

    public void setExtraMood(String extraMood) {
        this.extraMood = extraMood;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Long adviceId) {
        this.adviceId = adviceId;
    }

//    public Advice getAdvice() {
//        return advice;
//    }
//
//    public void setAdvice(Advice advice) {
//        this.advice = advice;
//    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
