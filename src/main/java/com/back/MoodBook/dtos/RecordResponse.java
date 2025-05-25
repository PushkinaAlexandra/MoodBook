package com.back.MoodBook.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class RecordResponse {
    private Long id;
    private LocalDateTime createAt;
    private String mood;
    private String extraMood;
    private String reason;

    public RecordResponse(Long id, LocalDateTime createdAt,
                          @NotBlank(message = "Choose Mood") String mood,
                          String extraMood, String reason) {
        this.id = id;
        this.createAt = createdAt;
        this.mood = mood;
        this.extraMood = extraMood;
        this.reason = reason;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
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
}
