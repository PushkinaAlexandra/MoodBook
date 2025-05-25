package com.back.MoodBook.dtos;

import jakarta.validation.constraints.NotBlank;

public class AdviceResponse {
    private Long id;
    private String name;
    private String content;
    private String mood;

    public AdviceResponse(Long id,
                          @NotBlank(message = "create cool name of advice") String name,
                          @NotBlank(message = "create text of advice") String content,
                          String mood) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.mood = mood;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
