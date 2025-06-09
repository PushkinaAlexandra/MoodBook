package com.back.MoodBook.service;

import com.back.MoodBook.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    // Внедряем репозиторий пользователя
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword()) // ВНИМАНИЕ: Никогда не используй plain text password в production!
                        .roles("USER") //  Предоставь нужные роли пользователю.
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}