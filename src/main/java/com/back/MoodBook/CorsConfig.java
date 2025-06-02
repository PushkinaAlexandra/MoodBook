package com.back.MoodBook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allow cookies
        config.addAllowedOrigin("http://localhost:63342"); // Разрешить запросы с вашего домена
        config.addAllowedMethod("*");  // Разрешить все методы (GET, POST, PUT, DELETE и т.д.)
        config.addAllowedHeader("*"); // Разрешить все заголовки

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}