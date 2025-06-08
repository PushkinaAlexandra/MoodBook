package com.back.MoodBook.dtos;

import jakarta.validation.constraints.NotBlank;

public class RegistrationRequest{
    @NotBlank(message = "fill username")
    private String username;
    @NotBlank(message = "fill password")
    private String password;

    public RegistrationRequest(){}

    public @NotBlank(message = "fill username") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "fill username") String username) {
        this.username = username;
    }

    public @NotBlank(message = "fill password") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "fill password") String password) {
        this.password = password;
    }
}