package com.back.MoodBook.dtos;

import jakarta.validation.constraints.NotBlank;

public class RecordCreateRequest {
    @NotBlank(message = "Choose Mood")
    private String mood;

    private String extraMood;

    private String reason;

    private String token;

    public RecordCreateRequest() {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}