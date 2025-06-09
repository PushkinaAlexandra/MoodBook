package com.back.MoodBook.dtos;

public class StringResponse {
    private String answer;

    public StringResponse(String answer){
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
