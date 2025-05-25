package com.back.MoodBook.dtos;

import jakarta.validation.constraints.NotBlank;

public class AdviceCreateRequest {
    @NotBlank(message = "create cool name of advice")
    private String name;

    @NotBlank(message = "create text of advice")
    private String content;

    //mood
    private String mood;

    public @NotBlank(message = "create cool name of advice") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "create cool name of advice") String name) {
        this.name = name;
    }

    public @NotBlank(message = "create text of advice") String getContent() {
        return content;
    }

    public void setContent(@NotBlank(message = "create text of advice") String content) {
        this.content = content;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
