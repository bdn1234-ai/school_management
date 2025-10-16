package com.hustuni.schoolmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Vô hiệu hóa CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())

                // 2. Cho phép TẤT CẢ các request http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Dòng này cho phép mọi request mà không cần đăng nhập
                );

        return http.build();
    }
}