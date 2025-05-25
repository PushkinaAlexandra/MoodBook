package com.back.MoodBook.dtos;

import jakarta.validation.constraints.NotBlank;

public class RecordCreateRequest {
    @NotBlank(message = "Choose Mood")
    private String mood;

    private String extraMood;

    private String reason;

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
}
