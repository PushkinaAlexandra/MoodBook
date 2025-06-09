package com.back.MoodBook.controllers;
import com.back.MoodBook.dtos.AuthResponse;
import com.back.MoodBook.dtos.LoginRequest;
import com.back.MoodBook.dtos.RegistrationRequest;
import com.back.MoodBook.dtos.StringResponse;
import com.back.MoodBook.entity.User;
import com.back.MoodBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public StringResponse register(@RequestBody RegistrationRequest registrationRequest) {
        User user = userService.registerUser(registrationRequest.getUsername(), registrationRequest.getPassword());
        if (user.getId() != 0){
            return  new StringResponse("User register");
        }
        return new StringResponse("User already exist");
    }

    @PostMapping("/login")
    public StringResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.findByUsername(loginRequest.username());
            if (user.getPassword().equals(loginRequest.password())){
                return new StringResponse("OK");
            }
            return new StringResponse("Wrong password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
